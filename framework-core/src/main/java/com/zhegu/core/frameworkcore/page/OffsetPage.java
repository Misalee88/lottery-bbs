package com.zhegu.core.frameworkcore.page;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 偏移量分页
 * </p>
 *
 * @author ZHIQIANG LI
 */
@Getter
@Setter
public class OffsetPage extends Page {

	/** 偏移数 */
	public int offset;

	/** 记录条数 */
	public int limit;

}
