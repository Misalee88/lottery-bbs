package com.zhegu.core.frameworkcore.util;

import java.util.HashMap;

/**
 * <p>
 * 中文转换数值
 * </p>
 * 
 * @author 张良【zhang.l1@haihangyun.com，QQ:66018777】
 * 
 */
public class ChineseNumberUtils {

	private static HashMap<String, String> NUMBER_MAP = new HashMap<String, String>();

	private static HashMap<String, String> UNIT_MAP = new HashMap<String, String>();

	static {
		NUMBER_MAP.put("0", "零");
		NUMBER_MAP.put("1", "一");
		NUMBER_MAP.put("2", "二");
		NUMBER_MAP.put("3", "三");
		NUMBER_MAP.put("4", "四");
		NUMBER_MAP.put("5", "五");
		NUMBER_MAP.put("6", "六");
		NUMBER_MAP.put("7", "七");
		NUMBER_MAP.put("8", "八");
		NUMBER_MAP.put("9", "九");
		UNIT_MAP.put("1", "十");
		UNIT_MAP.put("2", "百");
		UNIT_MAP.put("3", "千");
		UNIT_MAP.put("4", "万");
		UNIT_MAP.put("5", "十");
		UNIT_MAP.put("6", "百");
		UNIT_MAP.put("7", "千");
		UNIT_MAP.put("8", "亿");
	}

	private static String format(String number) {
		return convertToChineseNumber(number);
	}

	/**
	 * 
	 * <p>
	 * 格式化中文数值
	 * </p>
	 * 
	 * @param number
	 * @return String
	 */
	public static String format(int number) {
		return format(String.valueOf(number));
	}

	/**
	 * 
	 * <p>
	 * 格式化中文数值
	 * </p>
	 * 
	 * @param number
	 * @return String
	 */
	public static String format(long number) {
		return format(String.valueOf(number));
	}

	/**
	 * <p>
	 * 格式化中文数值
	 * </p>
	 * 
	 * @param number
	 * @return String
	 */
	private static String convertToChineseNumber(String number) {
		if (number == null) {
			return "";
		}
		String result;
		StringBuffer chineseNumber = new StringBuffer();
		char[] nchs = number.toCharArray();
		for (int i = 0; i < number.length(); i++) {
			chineseNumber.append(NUMBER_MAP.get(String.valueOf(nchs[i])));
		}
		int cursor = 1;
		for (int i = nchs.length - 1; i > 0; i--) {
			chineseNumber.insert(i, UNIT_MAP.get(String.valueOf(cursor)));
			cursor = cursor == 8 ? 1 : cursor + 1;
		}
		while (chineseNumber.indexOf("零十") != -1) {
			chineseNumber.replace(chineseNumber.indexOf("零十"), chineseNumber.indexOf("零十") + 2, "零");
		}
		while (chineseNumber.indexOf("零百") != -1) {
			chineseNumber.replace(chineseNumber.indexOf("零百"), chineseNumber.indexOf("零百") + 2, "零");
		}
		while (chineseNumber.indexOf("零千") != -1) {
			chineseNumber.replace(chineseNumber.indexOf("零千"), chineseNumber.indexOf("零千") + 2, "零");
		}
		while (chineseNumber.indexOf("零万") != -1) {
			chineseNumber.replace(chineseNumber.indexOf("零万"), chineseNumber.indexOf("零万") + 2, "万");
		}
		while (chineseNumber.indexOf("零亿") != -1) {
			chineseNumber.replace(chineseNumber.indexOf("零亿"), chineseNumber.indexOf("零亿") + 2, "亿");
		}
		while (chineseNumber.indexOf("零零") != -1) {
			chineseNumber.replace(chineseNumber.indexOf("零零"), chineseNumber.indexOf("零零") + 2, "零");
		}
		if (chineseNumber.lastIndexOf("零") == chineseNumber.length() - 1) {
			chineseNumber.delete(chineseNumber.length() - 1, chineseNumber.length());
		}
		result = chineseNumber.toString();
		if (result.startsWith("一十")) {
			return result.substring(1);
		}
		return result;
	}
}
