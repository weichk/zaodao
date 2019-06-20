/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * xiyang@yiji.com 2014年8月28日 下午3:20:21 创建
 */
package com.acooly.zaodao.common.utils;


import com.acooly.core.utils.Strings;

/**
 *
 *
 * @author xiyang
 *
 */
public class MaskStrUtils {
	
	/**
	 * 字符串掩码
	 * @param strSource 想要掩码的字符串
	 * @param mark 掩码字符
	 * @param beginIndex 掩码开始位置
	 * @param endIndex 掩码结束位置
	 * @return
	 */
	public static String maskStrBychar(String strSource, String mark, int beginIndex, int endIndex) {
		if (!Strings.isEmpty(strSource)) {
			String result = "";
			String replaceStr = "";
			int len = endIndex - beginIndex;
			for (int i = 0; i < len; i++) {
				replaceStr += mark;
			}
			if (replaceStr != "") {
				String front = strSource.substring(0, beginIndex);
				String behind = strSource.substring(endIndex, strSource.length());
				result = front + replaceStr + behind;
				return result;
			}
		}
		return strSource;
	}
	
	public static String maskCertNo(String strSource, String mark) {
		if (!Strings.isEmpty(strSource)) {
			String result = "";
			String replaceStr = "";
			if (strSource.length() > 4) {
				for (int i = 0, len = strSource.length() - 4; i < len; i++) {
					replaceStr += mark;
				}
				if (replaceStr != "") {
					String front = strSource.substring(0, 2);
					String behind = strSource.substring(strSource.length() - 2, strSource.length());
					result = front + replaceStr + behind;
					return result;
				}
			}
		}
		return strSource;
	};
	
	public static void main(String[] args) {
		System.out.println(maskCertNo("2144787879", "*"));
	}
}
