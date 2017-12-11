package com.zhegu.core.frameworkcore.page;

/**
 * <p>
 * 偏移量分页
 * </p>
 * 
 * @author 张良(zhang.l1@haihangyun.com) 2017年6月16日
 */
public class OffsetPage extends Page {

	/** 偏移数 */
	public int offset;

	/** 记录条数 */
	public int limit;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
