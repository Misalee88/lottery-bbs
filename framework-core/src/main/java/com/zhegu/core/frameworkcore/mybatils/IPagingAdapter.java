package com.zhegu.core.frameworkcore.mybatils;

/**
 * 分页适配器
 * 
 * @author ZHIQIANG LI
 */
public interface IPagingAdapter {

	String paging(String sql, int offset, int limit);
}
