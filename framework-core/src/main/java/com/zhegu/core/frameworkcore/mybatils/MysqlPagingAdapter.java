package com.zhegu.core.frameworkcore.mybatils;

public class MysqlPagingAdapter implements IPagingAdapter {

	@Override
	public String paging(String sql, int offset, int limit) {
		if (offset >= 0 && limit > 0) {
			StringBuffer pageSql = new StringBuffer();
			pageSql.append(sql);
			pageSql.append(" limit " + offset + "," + limit);
			return pageSql.toString();
		}
		return sql;
	}

}
