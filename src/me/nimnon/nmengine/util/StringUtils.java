package me.nimnon.nmengine.util;

import java.text.DecimalFormat;

public class StringUtils {
	public static String beautifyNumber(double num) {
		String ret = "";
		String sign = "";
		DecimalFormat dc = new DecimalFormat("0.00");

		if(num < 0) 
			sign = "-";
		
		Double number = Math.abs(num);

		ret = dc.format(number);
		
		if (number >= 1000d) {
			ret = dc.format(number / 1000d) + " Thousand";
		}
		if (number >= 1000000d) {
			ret = dc.format(number / 1000000d) + " Million";
		}
		if (number >= 1000000000d) {
			ret = dc.format(number / 1000000000d) + " Billion";
		}
		if (number >= 1000000000000d) {
			ret = dc.format(number / 1000000000000d) + " Trillion";
		}
		if (number >= 1000000000000000d) {
			ret = dc.format(number / 1000000000000000d) + " Quadrillion";
		}
		if (number >= 1000000000000000000d) {
			ret = dc.format(number / 1000000000000000000d) + " Quintillion";
		}
		if (number >= 1000000000000000000000d) {
			ret = dc.format(number / 1000000000000000000000d) + " Sextillion";
		}
		if (number >= 1000000000000000000000000d) {
			ret = dc.format(number / 1000000000000000000000000d) + " Octillion";
		}
		if (number >= 1000000000000000000000000000d) {
			ret = dc.format(number / 1000000000000000000000000000d) + " Nonillion";
		}
		if (number >= 1000000000000000000000000000000d) {
			ret = dc.format(number / 1000000000000000000000000000000d) + " Decillion";
		}
		if (number >= 1000000000000000000000000000000000d) {
			ret = dc.format(number / 1000000000000000000000000000000d) + " Undecillion";
		}
		if (number >= 1000000000000000000000000000000000000d) {
			ret = dc.format(number / 1000000000000000000000000000000000d) + " Duodecillion";
		}
		if (number >= 1000000000000000000000000000000000000000d) {
			ret = dc.format(number / 1000000000000000000000000000000000000d) + " Tredecillion";
		}
		if (number >= Math.pow(10d, 100)) {
			ret = dc.format(number / Math.pow(10d, 100)) + " Googol";
		}
		if (number >= Math.pow(Math.pow(10d, 100), 100)) {
			ret = dc.format(number / Math.pow(10d, 100)) + " Googolplex";
		}
		if (number >= Double.MAX_VALUE) {
			
		}

		return sign+ret;

	}

	public static String beautifyNumber(int number) {
		return beautifyNumber((double) number);
	}

	public static String beautifyNumber(float number) {
		return beautifyNumber((double) number);
	}

	public static String beautifyNumber(long number) {
		return beautifyNumber((double) number);
	}
}
