package com.zhegu.core.frameworkcore.page;

/**
 * <p>
 * 网格数据，带有页脚
 * </p>
 * 
 * @author 张良【Email：zhang.l1@haihangyun.com；QQ：66018777】
 */
public class NumberPage extends Page {

	/** 页码 */
	public int pageNumber;

	/** 页宽 **/
	public int pageSize;

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
