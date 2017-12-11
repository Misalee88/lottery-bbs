package com.zhegu.core.frameworkcore.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class RandomImage {

	private char[] codes;

	private BufferedImage image;

	private int digit;

	private int type;

	public int width;
	public int height;

	private Color bgColor;

	private Color color;

	public static final int TYPE_NUMBER = 1;

	public static final int TYPE_CHAR_BIG = 2;

	public static final int TYPE_CHAR_SMALL = 4;

	public static final Character[] NUMBER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	public static final Character[] BIG_CHAR = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	public static final Character[] SMALL_CHAR = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public RandomImage() {
		init();
	}

	public RandomImage(int digit) {
		this.digit = digit;
		init();
	}

	public RandomImage(int digit, int type) {
		this.digit = digit;
		this.type = type;
		init();
	}

	public RandomImage(int digit, int type, Color bgColor, Color color) {
		this.digit = digit;
		this.type = type;
		this.bgColor = bgColor;
		this.color = color;
		init();
	}

	private void init() {
		if (digit <= 0) {
			digit = 4;
		}
		if (type < 1) {
			type = 1;
		}
		codes = new char[digit];
		List<Character> clist = new ArrayList<Character>();
		if ((type & TYPE_NUMBER) == TYPE_NUMBER) {
			clist.addAll(Arrays.asList(NUMBER));
		}
		if ((type & TYPE_CHAR_BIG) == TYPE_CHAR_BIG) {
			clist.addAll(Arrays.asList(BIG_CHAR));
		}
		if ((type & TYPE_CHAR_SMALL) == TYPE_CHAR_SMALL) {
			clist.addAll(Arrays.asList(SMALL_CHAR));
		}
		int seed = clist.size();
		if (seed == 0) {
			return;
		}
		Random random = new Random();
		for (int i = 0; i < digit; i++) {
			int index = random.nextInt(seed);
			codes[i] = clist.get(index);
		}
	}

	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private void buildImage() {
		int width = this.width > 0 ? this.width : digit * 15 + 10, height = this.height > 0 ? this.height : 22;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		if (bgColor != null) {
			g.setColor(bgColor);
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			for (int i = 0; i < codes.length; i++) {
				Random random = new Random();
				g.setColor(color != null ? color : new Color(20 + random.nextInt(80), 20 + random.nextInt(80), 20 + random.nextInt(80))); // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
				g.drawString(String.valueOf(codes[i]), 20 * i + 8, height - random.nextInt(height / 3));
			}
		} else {
			g.setColor(new Color(255, 255, 255));
			// g.setColor(getRandColor(230, 240));
			g.fillRect(0, 0, width, height);
			// g.fillRoundRect(0, 0, width, height, 10, 10);
			g.setColor(new Color(100, 100, 100));
			g.drawRoundRect(0, 0, width - 1, height - 1, 10, 10);
			Random random = new Random();
			int w = width - 1;
			int h = height - 1;
			// 干扰素
			for (int i = 0; i < 100; i++) {
				g.setColor(getRandColor(50, 200));
				int x = random.nextInt(w);
				int y = random.nextInt(h);
				g.drawOval(x, y, 1, 1);
			}
			g.setFont(new Font("Arial", Font.BOLD, 20));
			for (int i = 0; i < codes.length; i++) {
				// 将码显示到图象中
				g.setColor(new Color(20 + random.nextInt(80), 20 + random.nextInt(80), 20 + random.nextInt(80))); // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
				g.drawString("" + codes[i], 15 * i + 8, 20 - random.nextInt(2));
			}
		}
		g.dispose();
	}

	public String getCode() {
		return new String(codes);
	}

	public BufferedImage getImage() {
		if (image == null) {
			buildImage();
		}
		return image;
	}
}
