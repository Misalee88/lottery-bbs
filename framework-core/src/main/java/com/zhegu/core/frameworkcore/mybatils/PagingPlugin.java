package com.zhegu.core.frameworkcore.mybatils;

import com.zhegu.core.frameworkcore.page.*;
import com.zhegu.core.frameworkcore.util.ReflectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Mybatis分页插件
 * </p>
 * 
 * @author 张良(zhang.l1@haihangyun.com)
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }),
		@Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }) })
public class PagingPlugin implements Interceptor {

	private static final Log log = LogFactory.getLog(PagingPlugin.class);

	private IPagingAdapter adapter;

	protected Page seekPage(Object parameter) {
		if (parameter != null && parameter instanceof Page) {
			return (Page) parameter;
		}
		return null;
	}

	@Override
	public Object intercept(Invocation ivk) throws Throwable {
		Object dlg = ReflectUtils.fieldValue(ivk.getTarget(), "delegate");
		MappedStatement mappedStatement = (MappedStatement) ReflectUtils.fieldValue(dlg, "mappedStatement");
		if (dlg == null || mappedStatement == null || !(dlg instanceof PreparedStatementHandler)) {
			return ivk.proceed();
		}
		PreparedStatementHandler delegate = (PreparedStatementHandler) dlg;
		BoundSql boundSql = delegate.getBoundSql();
		Object parameterObject = boundSql.getParameterObject();
		Page page = seekPage(parameterObject);
		if (page == null) {
			return ivk.proceed();
		}
		int offset = 0, limit = 0;
		if (page instanceof NumberPage) {
			NumberPage q = (NumberPage) page;
			if (q.pageSize <= 0 || q.pageNumber <= 0 || q.notPage) {
				return ivk.proceed();
			}
			offset = (q.pageNumber - 1) * q.pageSize;
			limit = q.pageSize;
		} else if (page instanceof OffsetPage) {
			OffsetPage p = (OffsetPage) page;
			offset = p.offset;
			limit = p.limit;
		}
		if (offset < 0 || limit <= 0) {
			return ivk.proceed();
		}
		if (ivk.getArgs()[0] instanceof Connection) {
			Configuration configuration = mappedStatement.getConfiguration();
			// 查询总数
			page.r.total = getTotalSize(ivk, configuration, mappedStatement, boundSql, parameterObject);
			log.debug("分页查询总记录数:" + page.r.total);
			// 修改SQL
			String newSql = adapter.paging(boundSql.getSql(), offset, limit);
			log.debug("分页查询语句:\r\n" + newSql);
			ReflectUtils.putFieldValue(boundSql, "sql", newSql);
			return ivk.proceed();
		}
		// 查询结果集
		return page.r.rows = (List<?>) ivk.proceed();
	}

	/**
	 * <p>
	 * 格式化总数SQL
	 * </p>
	 * 
	 * @param sql
	 * @return String
	 */
	private static String formatTotalSql(String sql) {
		if (Pattern.compile("\\sgroup\\sby\\s", Pattern.CASE_INSENSITIVE).matcher(sql).find()) {
			return "select count(*) as total from (" + sql + ") records";
		}
		Matcher orderByPtn = Pattern.compile("\\sorder\\sby\\s.*$", Pattern.CASE_INSENSITIVE).matcher(sql);
		if (orderByPtn.find()) {
			sql = orderByPtn.replaceAll("");
		}
		String[] forms = Pattern.compile("\\sfrom\\s", Pattern.CASE_INSENSITIVE).split(sql);
		Pattern selectPtn = Pattern.compile("(\\s|,|\\(|^)select\\s", Pattern.CASE_INSENSITIVE);
		int selectSize = 0;
		int fromIndex = -1;
		for (int i = 0; i < forms.length - 1; i++) {
			selectSize += selectPtn.split(forms[i]).length - 1;
			if (selectSize == 1) {
				fromIndex = i + 1;
				break;
			}
			selectSize -= 1;
		}
		if (fromIndex == -1) {
			fromIndex = forms.length - 1;
		}
		StringBuffer formatSql = new StringBuffer();
		formatSql.append("select count(*) as total");
		for (int i = fromIndex; i < forms.length; i++) {
			formatSql.append(" from ").append(forms[i]);
		}
		return formatSql.toString();
	}

	/**
	 * <p>
	 * 获取记录数
	 * </p>
	 * 
	 * @param ivk
	 * @param configuration
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @return
	 * @throws SQLException
	 */
	private int getTotalSize(Invocation ivk, Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		Connection conn = (Connection) ivk.getArgs()[0];
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalSize = 0;
		try {
			String totalSql = formatTotalSql(boundSql.getSql());
			ParameterHandler handler = configuration.newParameterHandler(mappedStatement, parameterObject, boundSql);
			pstmt = conn.prepareStatement(totalSql);
			handler.setParameters(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalSize = rs.getInt(1);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return totalSize;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	@Override
	public void setProperties(Properties p) {
		String adapter = p.getProperty("adapter");
		IPagingAdapter pa = null;
		if (adapter != null && !(adapter = adapter.trim()).isEmpty()) {
			try {
				pa = (IPagingAdapter) Class.forName(adapter).newInstance();
			} catch (Exception e) {
				log.error("无法解析分页适配器", e);
			}
		}
		this.adapter = pa != null ? pa : new MysqlPagingAdapter();
	}
}
