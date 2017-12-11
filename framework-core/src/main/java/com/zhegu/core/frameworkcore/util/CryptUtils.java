package com.zhegu.core.frameworkcore.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Formatter;

/**
 * <p>
 * 加密工具类
 * </p>
 * 
 * @author 张良【zhang.l1@haihangyun.com，QQ:66018777】
 * 
 */
public class CryptUtils {

	/**
	 * 
	 * <p>
	 * 检验密码是否匹配
	 * </p>
	 * <p>
	 * author 张良【Email：zhang.l1@haihangyun.com；QQ：66018777】
	 * </p>
	 * 
	 * @param source
	 *            明码
	 * @param sign
	 *            密码
	 * @return boolean
	 */
	public static boolean match(String source, String sign) {
		if (source == null || sign == null) {
			return false;
		}
		return source.equals(decode(sign));
	}

	/**
	 * <p>
	 * 编码
	 * </p>
	 * <p>
	 * author 张良(zhang.l1@haihangyun.com)
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		try {
			byte[] key = "XuAndLuo".getBytes();
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DES/CBC/PKCS5Padding");
			javax.crypto.spec.DESKeySpec desKeySpec = new javax.crypto.spec.DESKeySpec(key);
			javax.crypto.SecretKeyFactory keyFactory = javax.crypto.SecretKeyFactory.getInstance("DES");
			javax.crypto.SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			javax.crypto.spec.IvParameterSpec iv = new javax.crypto.spec.IvParameterSpec(key);
			cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey, iv);
			byte[] encPswd = cipher.doFinal(str.getBytes());
			StringBuilder stringBuilder = new StringBuilder();
			if (encPswd == null || encPswd.length <= 0) {
				return null;
			}
			for (int i = 0; i < encPswd.length; i++) {
				int v = encPswd[i] & 0xFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2) {
					stringBuilder.append(0);
				}
				stringBuilder.append(hv);
			}
			return stringBuilder.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str;
	}

	/**
	 * <p>
	 * 解码解码
	 * </p>
	 * <p>
	 * author 张良【zhang.l1@haihangyun.com，QQ:66018777】
	 * </p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String decode(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		try {
			String us = str.toUpperCase();
			int length = us.length() / 2;
			byte[] bdata = new byte[length];
			for (int i = 0; i < length; i++) {
				bdata[i] = (byte) Integer.parseInt(us.substring(2 * i, 2 * (i + 1)), 16);
			}
			byte[] key = "XuAndLuo".getBytes();
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DES/CBC/PKCS5Padding");
			javax.crypto.spec.DESKeySpec desKeySpec = new javax.crypto.spec.DESKeySpec(key);
			javax.crypto.SecretKeyFactory keyFactory = javax.crypto.SecretKeyFactory.getInstance("DES");
			javax.crypto.SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			javax.crypto.spec.IvParameterSpec iv = new javax.crypto.spec.IvParameterSpec(key);
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey, iv);
			byte[] retByte = cipher.doFinal(bdata);
			return new String(retByte).trim();
		} catch (Exception ex) {
		}
		return str;
	}

	// /**
	// * <p>
	// * MD5加密
	// * </p>
	// * <p>
	// * author 张良(zhang.l1@haihangyun.com)
	// * </p>
	// *
	// * @param str
	// * @return
	// */
	// public static String md5(String str) {
	// if (str == null || str.isEmpty()) {
	// return null;
	// }
	// try {
	// MessageDigest md = MessageDigest.getInstance("md5");
	// byte[] bytes = md.digest(str.getBytes("UTF-8"));
	// StringBuffer buf = new StringBuffer();
	// for (int i = 0; i < bytes.length; i++) {
	// int v = bytes[i] & 0xFF;
	// String hv = Integer.toHexString(v);
	// if (hv.length() < 2) {
	// buf.append(0);
	// }
	// buf.append(hv);
	// }
	// return buf.toString().toUpperCase();
	// } catch (Exception e) {
	// }
	// return null;
	// }

	public static String md5(File file) {
		FileInputStream in = null;
		String smd5 = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			FileChannel ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			md5.update(byteBuffer);
			byte[] hash = md5.digest();
			Formatter formatter = new Formatter();
			for (byte b : hash) {
				formatter.format("%02X", b);
			}
			smd5 = formatter.toString();
			formatter.close();
		} catch (Exception e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return smd5;
	}

	public static String md5(String s) {
		if (s == null || s.isEmpty()) {
			return null;
		}
		return md5(s.getBytes());
	}

	public static String md5(byte[] bytes) {
		String smd5 = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] hash = md5.digest();
			Formatter formatter = new Formatter();
			for (byte b : hash) {
				formatter.format("%02X", b);
			}
			smd5 = formatter.toString();
			formatter.close();
		} catch (Exception e) {
		}
		return smd5;
	}
}
