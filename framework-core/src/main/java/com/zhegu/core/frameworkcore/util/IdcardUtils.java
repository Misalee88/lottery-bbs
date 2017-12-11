package com.zhegu.core.frameworkcore.util;

import java.util.HashMap;
import java.util.Map;

public class IdcardUtils {

	private static Map<String, String> genders = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("0", "未知");
			put("1", "男");
			put("2", "女");
			put("9", "未说明");
		}
	};

	private static Map<String, String> ethnicities = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("01", "汉族");
			put("02", "蒙古族");
			put("03", "回族");
			put("04", "藏族");
			put("05", "维吾尔族");
			put("06", "苗族");
			put("07", "彝族");
			put("08", "壮族");
			put("09", "布依族");
			put("10", "朝鲜族");
			put("11", "满族");
			put("12", "侗族");
			put("13", "瑶族");
			put("14", "白族");
			put("15", "土家族");
			put("16", "哈尼族");
			put("17", "哈萨克族");
			put("18", "傣族");
			put("19", "黎族");
			put("20", "傈僳族");
			put("21", "佤族");
			put("22", "畲族");
			put("23", "高山族");
			put("24", "拉祜族");
			put("25", "水族");
			put("26", "东乡族");
			put("27", "纳西族");
			put("28", "景颇族");
			put("29", "柯尔克孜族");
			put("30", "土族");
			put("31", "达斡尔族");
			put("32", "仫佬族");
			put("33", "羌族");
			put("34", "布朗族");
			put("35", "撒拉族");
			put("36", "毛南族");
			put("37", "仡佬族");
			put("38", "锡伯族");
			put("39", "阿昌族");
			put("40", "普米族");
			put("41", "塔吉克族");
			put("42", "怒族");
			put("43", "乌孜别克族");
			put("44", "俄罗斯族");
			put("45", "鄂温克族");
			put("46", "德昂族");
			put("47", "保安族");
			put("48", "裕固族");
			put("49", "京族");
			put("50", "塔塔尔族");
			put("51", "独龙族");
			put("52", "鄂伦春族");
			put("53", "赫哲族");
			put("54", "门巴族");
			put("55", "珞巴族");
			put("56", "基诺族");
		}
	};

	public static String getGenderValue(String key) {
		if (key == null) {
			return null;
		}
		return genders.get(key);
	}

	public static String getEthnicValue(String key) {
		if (key == null) {
			return null;
		}
		return ethnicities.get(key);
	}

	public static String getGenderKey(String value) {
		if (value != null) {
			for (String key : genders.keySet()) {
				if (value.equals(genders.get(key))) {
					return key;
				}
			}
		}
		return null;
	}

	public static String getEthnicKey(String value) {
		if (value != null) {
			String v, val = value + "族";
			for (String key : genders.keySet()) {
				v = ethnicities.get(key);
				if (value.equals(v) || val.equals(v)) {
					return key;
				}
			}
		}
		return null;
	}

}
