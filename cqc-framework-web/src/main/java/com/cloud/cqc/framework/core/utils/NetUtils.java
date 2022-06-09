package com.cloud.cqc.framework.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.cloud.cqc.framework.core.lang.ValidateRegexp;

public class NetUtils {

	/** IP v4 */
	public final static Pattern IPV4 = Pattern.compile(ValidateRegexp.IPV4);

	/**
	 * 
	 * 根据long值获取ip v4地址
	 * 
	 * 
	 * 
	 * @param longIP
	 *            IP的long表示形式
	 * 
	 * @return IP V4 地址
	 * 
	 */
	public static String longToIpv4(long longIP) {
		StringBuffer sb = new StringBuffer();
		// 直接右移24位

		sb.append(String.valueOf(longIP >>> 24));
		sb.append(".");
		// 将高8位置0，然后右移16位

		sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longIP & 0x000000FF));
		return sb.toString();
	}

	/**
	 * 
	 * 根据ip地址计算出long型的数据
	 * 
	 * 
	 * 
	 * @param strIP
	 *            IP V4 地址
	 * 
	 * @return long值
	 * 
	 */
	public static long ipv4ToLong(String strIP) {

		if (!IPV4.matcher(strIP).find()) {
			return 0;
		}

		long[] ip = new long[4];
		// 先找到IP地址字符串中.的位置

		int position1 = strIP.indexOf(".");
		int position2 = strIP.indexOf(".", position1 + 1);
		int position3 = strIP.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型

		ip[0] = Long.parseLong(strIP.substring(0, position1));
		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIP.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 * 指定IP的long是否在指定范围内
	 * 
	 * @param userIp
	 *            用户IP
	 * @param begin
	 *            开始IP
	 * @param end
	 *            结束IP
	 * @return 是否在范围内
	 */
	public static boolean isInner(String userIp, String begin, String end) {
		return isInner(ipv4ToLong(userIp), ipv4ToLong(begin), ipv4ToLong(end));
	}

	/**
	 * 指定IP的long是否在指定范围内
	 * 
	 * @param userIp
	 *            用户IP
	 * @param begin
	 *            开始IP
	 * @param end
	 *            结束IP
	 * @return 是否在范围内
	 */
	public static boolean isInner(long userIp, long begin, long end) {
		return (userIp >= begin) && (userIp <= end);
	}

	/**
	 * 获取请求ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

}
