package com.spring.utils;

public class ColorUtil {
	public static String hexToRgba(String hex, float alpha) {
		hex = hex.replace("#", "");
		int r = Integer.parseInt(hex.substring(0, 2), 16);
		int g = Integer.parseInt(hex.substring(2, 4), 16);
		int b = Integer.parseInt(hex.substring(4, 6), 16);
		return String.format("rgba(%d, %d, %d, %.2f)", r, g, b, alpha);
	}
}
