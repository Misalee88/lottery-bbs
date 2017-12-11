package com.zhegu.core.frameworkcore.util;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>
 * 功能类
 * </p>
 * <div>create time: 2013-1-27 下午2:51:07</div>
 * 
 * @author 张良（zhang.l1@haihangyun.com）
 */
public class Utils {

	/**
	 * 生成UUID
	 * 
	 * @return String
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 读取流字节
	 * 
	 * @param is
	 * @return byte[]
	 */
	public static byte[] readInputStream(InputStream is) {
		if (is == null) {
			return null;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] val = null;
		byte[] bs = new byte[1024];
		int len;
		try {
			while ((len = is.read(bs)) > 0) {
				bos.write(bs, 0, len);
			}
			val = bos.toByteArray();
		} catch (Exception e) {
		} finally {
			try {
				bos.close();
			} catch (Exception e) {
			}
		}
		return val;
	}

	public static byte[] readBytes(InputStream in, int length) throws IOException {
		byte[] bytes = new byte[length];
		int len, off = 0;
		while ((len = in.read(bytes, off, length - off)) != -1) {
			off += len;
			if (off >= length) {
				break;
			}
		}
		return bytes;
	}

	public static void readToWrite(InputStream in, OutputStream out, long length) throws IOException {
		byte[] bytes = new byte[1024];
		int len, off = 0;
		while ((len = in.read(bytes)) != -1) {
			out.write(bytes, 0, len);
			off += len;
			if (off >= length) {
				break;
			}
		}
	}

	public static int readInt(InputStream in) throws IOException {
		byte[] bytes = new byte[4];
		int len, off = 0;
		while ((len = in.read(bytes, off, 4 - off)) != -1) {
			off += len;
			if (off >= 4) {
				break;
			}
		}
		return toInt(bytes);
	}

	/**
	 * <p>
	 * 格式化 ids列表，因为jpa更新id只允许1000以下，所以提供该方法
	 * </p>
	 * <p>
	 * author 张良【Email：zhang.l1@haihangyun.com；QQ：66018777】
	 * </p>
	 * 
	 * @param ids
	 * @param size
	 * @return List
	 */
	public static List<List<String>> formatToList(String[] ids, int size) {
		if (ids == null || ids.length == 0) {
			return new ArrayList<List<String>>();
		}
		if (size <= 0) {
			List<List<String>> list = new ArrayList<List<String>>();
			list.add(Arrays.asList(ids));
			return list;
		}
		int group = ids.length / size;
		int remainer = ids.length % size;
		if (remainer > 0) {
			group += 1;
		}
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> vals;
		int index;
		for (int i = 0; i < group; i++) {
			vals = new ArrayList<String>();
			for (int j = 0; j < size; j++) {
				if ((index = i * size + j) >= ids.length) {
					break;
				}
				vals.add(ids[index]);
			}
			if (vals.isEmpty()) {
				break;
			}
			list.add(vals);
		}
		return list;
	}

	public static int toInt(byte[] bytes) {
		int num = 0;
		for (int i = 0; i < 4; i++) {
			num |= (bytes[i] & 0xFF) << ((3 - i) * 8);
		}
		return num;
	}

	public static byte[] toBytes(int num) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) ((num >> ((3 - i) * 8)) & 0xFF);
		}
		return b;
	}

	public static byte[] fillBytes(String in, int length, byte b) {
		byte[] bs = new byte[length];
		byte[] inbs = in.getBytes();
		for (int i = 0; i < length; i++) {
			if (inbs.length > i) {
				bs[i] = inbs[i];
			} else {
				bs[i] = b;
			}
		}
		return bs;
	}

	public static String b2h(byte[] data) {
		if (data == null || data.length == 0) {
			return "";
		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int v = data[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				buf.append(0);
			}
			buf.append(hv);
		}
		return buf.toString();
	}

	public static byte[] h2b(String hex) {
		if (hex == null || hex.isEmpty()) {
			return new byte[0];
		}
		String us = hex.toUpperCase();
		int length = us.length() / 2;
		byte[] bdata = new byte[length];
		for (int i = 0; i < length; i++) {
			bdata[i] = (byte) Integer.parseInt(us.substring(2 * i, 2 * (i + 1)), 16);
		}
		return bdata;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean isNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public static boolean isMobile(String mobile) {
		return Pattern.matches("1[3-8][0-9]{9}", mobile);
	}
}
