package com.zhegu.core.frameworkcore.util;

import java.util.*;

/**
 * 随机码工具类
 * 
 * @author 张良
 * 
 */
public class RandomUtils {

	public static final int TYPE_NUMBER = 1;

	public static final int TYPE_CHAR_BIG = 2;

	public static final int TYPE_CHAR_SMALL = 4;

	public static final char[] NUMBER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static final char[] BIG_CHAR = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static final char[] SMALL_CHAR = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/**
	 * 生成随机码
	 * 
	 * @param digit
	 *            位数
	 * @param type
	 *            字符类型，1-数字，2-大写字母，4-小写字母，允许或计算，如：5=1|4=数字或小写字母
	 * @return String
	 */
	public static String random(int digit, int type) {
		if (digit <= 0) {
			digit = 4;
		}
		if (type < 1) {
			type = 1;
		}
		char[] chs = new char[digit];
		List<Character> clist = new ArrayList<Character>();
		if ((type & TYPE_NUMBER) == TYPE_NUMBER) {
			for (char ch : NUMBER) {
				clist.add(ch);
			}
		}
		if ((type & TYPE_CHAR_BIG) == TYPE_CHAR_BIG) {
			for (char ch : BIG_CHAR) {
				clist.add(ch);
			}
		}
		if ((type & TYPE_CHAR_SMALL) == TYPE_CHAR_SMALL) {
			for (char ch : SMALL_CHAR) {
				clist.add(ch);
			}
		}
		int seed = clist.size();
		if (seed == 0) {
			return "";
		}
		Random random = new Random();
		for (int i = 0; i < digit; i++) {
			int index = random.nextInt(seed);
			chs[i] = clist.get(index);
		}
		return new String(chs);
	}
}
