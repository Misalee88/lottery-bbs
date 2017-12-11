package com.zhegu.core.frameworkcore.mybatils;

import org.apache.ibatis.type.*;

import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * Blob字段处理
 *
 * @author ZHIQIANG LI
 * 
 */
@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.BLOB)
public class BlobTypeHandler extends BaseTypeHandler<String> {

	private static final String DEFAULT_CHARSET = "utf-8";

	@Override
	public void setNonNullParameter(PreparedStatement pstmt, int parameterIndex, String parameter, JdbcType jdbcType) throws SQLException {
		try {
			pstmt.setBytes(parameterIndex, parameter.getBytes(DEFAULT_CHARSET));
		} catch (UnsupportedEncodingException e) {
			pstmt.setBytes(parameterIndex, parameter.getBytes());
		}
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		byte[] bytes = rs.getBytes(columnName);
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return new String(bytes);
		}
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		byte[] bytes = rs.getBytes(columnIndex);
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return new String(bytes);
		}
	}

	@Override
	public String getNullableResult(CallableStatement cstmt, int columnIndex) throws SQLException {
		byte[] bytes = cstmt.getBytes(columnIndex);
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return new String(bytes);
		}
	}

}
