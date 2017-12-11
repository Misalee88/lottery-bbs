package com.zhegu.core.frameworkcore.util;

import java.util.List;

/**
 * <p>
 * 图形计算工具
 * </p>
 * 
 * @author 张良(zhang.l1@haihangyun.com) 2017年5月16日
 */
public class GeoUtils {

	/**
	 * <p>
	 * 坐标
	 * </p>
	 * 
	 * @author 张良(zhang.l1@haihangyun.com) 2017年5月16日
	 */
	public static class P {
		public double x;
		public double y;

		public P(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	/**
	 * <p>
	 * 射线法判断点是否在多边形内部
	 * </p>
	 * <p>
	 * author 张良(zhang.l1@haihangyun.com)
	 * </p>
	 * 
	 * @param p
	 *            待判断的点，格式：{ x: X坐标, y: Y坐标 }
	 * @param polygon
	 *            多边形顶点，数组成员的格式同 p
	 * @return
	 */
	public static boolean isIn(P p, List<P> polygon) {
		double px = p.x, py = p.y;
		boolean flag = false;
		P p1, p2;
		for (int i = 0, l = polygon.size(), j = l - 1; i < l; j = i, i++) {
			p1 = polygon.get(i);
			p2 = polygon.get(j);
			double sx = p1.x, sy = p1.y, tx = p2.x, ty = p2.y;
			// 点与多边形顶点重合
			if ((sx == px && sy == py) || (tx == px && ty == py)) {
				return true;
			}
			// 判断线段两端点是否在射线两侧
			if ((sy < py && ty >= py) || (sy >= py && ty < py)) {
				// 线段上与射线 Y 坐标相同的点的 X 坐标
				double offsetx = sx + (py - sy) * (tx - sx) / (ty - sy) - px;
				// 点在多边形的边上
				if (offsetx == 0) {
					return true;
				}
				// 射线穿过多边形的边界
				if (offsetx > 0) {
					flag = !flag;
				}
			}
		}
		// 射线穿过多边形边界的次数为奇数时点在多边形内
		return flag;
	}
	//
	// public static void main(String[] args) {
	// List<P> ps = new ArrayList<>();
	// ps.add(new P(20.021083, 110.341538));
	// ps.add(new P(20.021274, 110.341201));
	// ps.add(new P(20.021363, 110.341826));
	// ps.add(new P(20.021542, 110.341682));
	// ps.add(new P(20.021588, 110.341219));
	// ps.add(new P(20.021754, 110.341543));
	// ps.add(new P(20.02642, 110.212771));
	// ps.add(new P(20.026628, 110.212065));
	// ps.add(new P(20.026976, 110.213548));
	// ps.add(new P(20.027188, 110.211949));
	// ps.add(new P(20.027273, 110.212735));
	// ps.add(new P(20.027392, 110.212515));
	// ps.add(new P(20.027464, 110.213202));
	// ps.add(new P(20.027629, 110.212519));
	//
	// System.out.println(isIn(new P(20.321389, 110.341426), ps));
	// System.out.println(isIn(new P(20.021389, 110.341426), ps));
	// }
}
