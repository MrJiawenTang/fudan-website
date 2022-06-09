package com.cloud.cqc.framework.core.utils;

import java.security.MessageDigest;

/**
 * 
 * @author joy.zhou
 * @date 2017年12月14日
 * @version 1.0
 */
public class MD5Encoder {

	/**
	 * 获取md5
	 * 
	 * @param source
	 * @return
	 */
	public static String encode(byte[] source) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		byte[] md5Bytes = md5.digest(source);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}
}
