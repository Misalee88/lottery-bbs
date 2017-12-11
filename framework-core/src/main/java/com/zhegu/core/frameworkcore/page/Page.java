package com.zhegu.core.frameworkcore.page;

/**
 * <p>
 * 分页信息
 * </p>
 * 
 * @author 张良(zhang.l1@haihangyun.com) 2017年6月16日
 */
public class Page {

	/** 是否分页 */
	public boolean notPage;

	/** 排序名称 */
	public String sort;

	/** 排序列表 */
	public String order;

	public PageR r = new PageR();

	public void setNotPage(boolean notPage) {
		this.notPage = notPage;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
