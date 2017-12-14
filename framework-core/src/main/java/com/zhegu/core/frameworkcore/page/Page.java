package com.zhegu.core.frameworkcore.page;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分页信息
 * </p>
 * 
 * @author ZHIQIANG LI
 */
@Getter
@Setter
public class Page {

	/** 是否分页 */
	public boolean notPage;

	/** 排序名称 */
	public String sort;

	/** 排序列表 */
	public String order;

	public PageR r = new PageR();

}
