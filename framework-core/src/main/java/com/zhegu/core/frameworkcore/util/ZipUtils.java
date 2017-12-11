package com.zhegu.core.frameworkcore.util;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipUtils {

	private static final Logger LOG = Logger.getLogger(ZipUtils.class.getName());

	public static byte[] zip(byte[] bytes) {
		byte[] out = null;
		ByteArrayOutputStream bos = null;
		GZIPOutputStream gz = null;
		try {
			bos = new ByteArrayOutputStream();
			gz = new GZIPOutputStream(bos);
			gz.write(bytes);
			gz.finish();
			out = bos.toByteArray();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "数据压缩出错", e);
		} finally {
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
				}
			}
		}
		return out;
	}

	public static void zip(byte[] bytes, OutputStream out, String entryName) {
		GZIPOutputStream gz = null;
		try {
			gz = new GZIPOutputStream(out);
			gz.write(bytes);
			gz.finish();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "数据压缩出错", e);
		} finally {
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static byte[] zip(String filename) {
		byte[] out = null;
		ByteArrayOutputStream bos = null;
		GZIPOutputStream gz = null;
		FileInputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			gz = new GZIPOutputStream(bos);
			os = new FileInputStream(filename);
			byte buf[] = new byte[1024];
			int c = 0;
			while ((c = os.read(buf, 0, buf.length)) > 0) {
				gz.write(buf, 0, c);
			}
			gz.finish();
			out = bos.toByteArray();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "文件压缩出错", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
				}
			}
		}
		return out;
	}

	public static void zip(String filename, OutputStream out) {
		GZIPOutputStream gz = null;
		FileInputStream os = null;
		try {
			gz = new GZIPOutputStream(out);
			os = new FileInputStream(filename);
			byte buf[] = new byte[1024];
			int c = 0;
			while ((c = os.read(buf, 0, buf.length)) > 0) {
				gz.write(buf, 0, c);
			}
			gz.finish();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "文件压缩出错", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * <p>
	 * 压缩文件
	 * </p>
	 * 
	 * @param src
	 * @param dest
	 * @date 2015年8月8日
	 */
	public static void zip(String src, String dest) {
		GZIPOutputStream gz = null;
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(dest);
			gz = new GZIPOutputStream(out);
			in = new FileInputStream(src);
			byte buf[] = new byte[1024];
			int c = 0;
			while ((c = in.read(buf, 0, buf.length)) > 0) {
				gz.write(buf, 0, c);
			}
			gz.finish();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "文件压缩出错", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void unzip(byte[] in, OutputStream out) {
		GZIPInputStream gz = null;
		ByteArrayInputStream bois = null;
		try {
			bois = new ByteArrayInputStream(in);
			gz = new GZIPInputStream(bois, in.length);
			byte buf[] = new byte[1024];
			int c = 0;
			while ((c = gz.read(buf, 0, buf.length)) > 0) {
				out.write(buf, 0, c);
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "文件压缩出错", e);
		} finally {
			if (bois != null) {
				try {
					bois.close();
				} catch (IOException e) {
				}
			}
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void unzip(InputStream in, int size, OutputStream out) {
		GZIPInputStream gz = null;
		try {
			gz = new GZIPInputStream(in, size);
			byte buf[] = new byte[1024];
			int c = 0;
			while ((c = gz.read(buf, 0, buf.length)) > 0) {
				out.write(buf, 0, c);
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "文件压缩出错", e);
		} finally {
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void unzip(byte[] in, String filename) {
		FileOutputStream fos = null;
		try {
			unzip(in, fos = new FileOutputStream(filename));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "文件压缩出错", e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void unzip(InputStream in, int size, String filename) {
		FileOutputStream fos = null;
		try {
			unzip(in, size, fos = new FileOutputStream(filename));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "文件压缩出错", e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * <p>
	 * 解压缩文件
	 * </p>
	 * 
	 * @param src
	 * @param dest
	 * @date 2015年8月5日
	 */
	public static void unzip(String src, String dest) {
		GZIPInputStream gz = null;
		InputStream in = null;
		FileOutputStream fos = null;
		try {
			gz = new GZIPInputStream(in = new FileInputStream(src));
			fos = new FileOutputStream(dest);
			byte buf[] = new byte[1024];
			int c = 0;
			while ((c = gz.read(buf, 0, buf.length)) > 0) {
				fos.write(buf, 0, c);
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "文件压缩出错", e);
		} finally {
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
