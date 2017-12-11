package com.zhegu.core.frameworkcore.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.*;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Json工具类
 * </p>
 * <div>create time: 2013-1-23 下午1:52:05</div>
 * 
 * @author 张良（zhang.l1@haihangyun.com）
 */
public class JsonUtils {

	private final static Logger log = Logger.getLogger(JsonUtils.class.getName());

	/**
	 * java对象转换成json对象字符串
	 * 
	 * @param obj
	 * @return String
	 */
	public static String toString(Object obj) {
		if (obj == null) {
			return "";
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.log(Level.INFO, "解析Json出错", e);
			return "";
		}
	}

	/**
	 *
	 * @param obj
	 * @return
	 */
	public static String toStringIncludeNull(Object obj) {
		if (obj == null) {
			return "";
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.log(Level.INFO, "解析Json出错", e);
			return "";
		}
	}

	/**
	 * java对象转换成json对象字节数组
	 * 
	 * @param obj
	 * @return String
	 */
	public static byte[] toBytes(Object obj) {
		if (obj == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			return mapper.writeValueAsBytes(obj);
		} catch (Exception e) {
			log.log(Level.INFO, "解析Json出错", e);
			return null;
		}
	}

	/**
	 * <p>
	 * 解析为对象
	 * </p>
	 * 
	 * @param in
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(java.io.InputStream in, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(in, clazz);
		} catch (Exception e) {
			log.log(Level.INFO, "解析Json出错", e);
			return null;
		}
	}

	/**
	 * <p>
	 * 解析为对象
	 * </p>
	 * 
	 * @param in
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(byte[] in, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(in, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			log.log(Level.INFO, "解析Json出错", e);
			return null;
		}
	}

	/**
	 * <p>
	 * 解析为对象
	 * </p>
	 * 
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String str, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(str, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			log.log(Level.INFO, "解析Json出错", e);
			return null;
		}
	}
}
