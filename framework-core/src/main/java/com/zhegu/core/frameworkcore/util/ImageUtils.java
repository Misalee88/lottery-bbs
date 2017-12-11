package com.zhegu.core.frameworkcore.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtils {

	private static String formatName(String name) {
		if (name == null || name.length() == 0) {
			name = "png";
		} else {
			int lastDot = name.lastIndexOf('.');
			if (lastDot != -1) {
				name = name.substring(0, lastDot);
			}
		}
		String fn = name.toLowerCase();
		if (fn.indexOf("jpeg") != -1 || fn.indexOf("jpg") != -1) {
			name = "jpeg";
		} else if (fn.indexOf("wbmp") != -1) {
			name = "wbmp";
		} else if (fn.indexOf("bmp") != -1) {
			name = "bmp";
		} else if (fn.indexOf("gif") != -1) {
			name = "gif";
		} else {
			name = "png";
		}
		return name;
	}

	/**
	 * 图片拷贝
	 * 
	 * @param originalImage
	 * @param scaledWidth
	 * @param scaledHeight
	 * @return BufferedImage
	 */
	public static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight) {
		int width = originalImage.getWidth(null);
		int height = originalImage.getHeight(null);
		if (scaledWidth <= 0) {
			scaledWidth = width;
		}
		if (scaledHeight <= 0) {
			scaledHeight = height;
		}
		if (originalImage instanceof BufferedImage) {
			if (width == scaledWidth && height == scaledHeight) {
				return (BufferedImage) originalImage;
			}
			AffineTransform atf = AffineTransform.getScaleInstance((double) scaledWidth / width, (double) scaledHeight / height);
			AffineTransformOp op = new AffineTransformOp(atf, null);
			return op.filter((BufferedImage) originalImage, null);
		}
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = scaledBI.createGraphics();
		g2d.setComposite(AlphaComposite.Src);
		g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();
		return scaledBI;
	}

	/**
	 * <p>
	 * 图片转换
	 * </p>
	 * 
	 * @param in
	 *            源图片输入流
	 * @param out
	 *            目标图片输出流
	 * @param scaleWidth
	 *            压缩宽度
	 * @param scaledHeight
	 *            压缩高度
	 * @param formatName
	 *            压缩文件格式，GIF、JPG、PNG
	 */
	public static void createResizedCopy(InputStream in, OutputStream out, int scaleWidth, int scaledHeight, String formatName) {
		try {
			BufferedImage bufferedImage = ImageIO.read(in);
			if (scaleWidth <= 0 && scaledHeight <= 0) {
				ImageIO.write(bufferedImage, formatName(formatName), out);
			} else {
				if (scaleWidth > 0 && scaledHeight <= 0) {
					scaledHeight = scaleWidth * bufferedImage.getHeight() / bufferedImage.getWidth();
				}
				if (scaleWidth <= 0 && scaledHeight > 0) {
					scaleWidth = scaledHeight * bufferedImage.getWidth() / bufferedImage.getHeight();
				}
				BufferedImage formatImage = createResizedCopy(bufferedImage, scaleWidth, scaledHeight);
				ImageIO.write(formatImage, formatName(formatName), out);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * <p>
	 * 图片转换
	 * </p>
	 * 
	 * @param in
	 *            源图片输入流
	 * @param scaleWidth
	 *            压缩宽度
	 * @param scaledHeight
	 *            压缩高度
	 * @param formatName
	 *            压缩文件格式，GIF、JPG、PNG
	 */
	public static byte[] createResizedCopy(InputStream in, int scaleWidth, int scaledHeight, String formatName) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			createResizedCopy(in, baos, scaleWidth, scaledHeight, formatName);
			byte[] bytes = baos.toByteArray();
			baos.close();
			return bytes;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>
	 * 缩放
	 * </p>
	 * <p>
	 * author 张良(zhang.l1@haihangyun.com)
	 * </p>
	 * 
	 * @param is
	 * @param out
	 * @param scale
	 * @param formatName
	 */
	public static void createResizedCopy(InputStream is, OutputStream out, float scale, String formatName) {
		try {
			BufferedImage bufferedImage = ImageIO.read(is);
			if (scale <= 0) {
				ImageIO.write(bufferedImage, formatName(formatName), out);
			} else {
				int scaleWidth = (int) (bufferedImage.getWidth() * scale);
				int scaledHeight = (int) (bufferedImage.getHeight() * scale);
				BufferedImage formatImage = createResizedCopy(bufferedImage, scaleWidth, scaledHeight);
				ImageIO.write(formatImage, formatName(formatName), out);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * <p>
	 * 缩放
	 * </p>
	 * <p>
	 * author 张良(zhang.l1@haihangyun.com)
	 * </p>
	 * 
	 * @param is
	 * @param scale
	 * @param formatName
	 * @return
	 */
	public static byte[] createResizedCopy(InputStream is, float scale, String formatName) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			createResizedCopy(is, baos, scale, formatName);
			byte[] bytes = baos.toByteArray();
			baos.close();
			return bytes;
		} catch (Exception e) {
			return null;
		}
	}

}
