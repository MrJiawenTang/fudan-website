package com.cloud.cqc.framework.core.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * 
 * @author joy.zhou
 * @date 2017年7月17日
 * @version 1.0
 */
public class DateUtils {
	/** yyyy-MM-dd HH:mm:ss */
	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	/** yyyyMMddHHmmss */
	public static final DateTimeFormatter DATE_TIME_LANG_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	/** HH:mm */
	public static final DateTimeFormatter TIME_NOSECOND = DateTimeFormatter.ofPattern("HH:mm");
	/** 星期中文 */
	private static final String[] WEEKDAYS = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 距离当前多少天
	 * 
	 * @param dateStr
	 *            日期
	 * @return
	 */
	public static int offsetNowDay(LocalDate date) {

		return LocalDate.now().until(date).getDays();
	}

	/**
	 * 判断当前日期是否在起始日期与结束日期之间
	 * 
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 * @throws ParseException
	 */
	public static boolean isBewteen(String beginDate, String endDate) {
		LocalDate now = LocalDate.now();
		return now.compareTo(LocalDate.parse(beginDate)) >= 0 && now.compareTo(LocalDate.parse(endDate)) <= 0;

	}

	/**
	 * 获取星期(中文)
	 * 
	 * @param weekday
	 *            参考Calender星期
	 * @return
	 */
	public static String getWeekDayFormat(Integer weekday) {

		return WEEKDAYS[weekday - 1];
	}

	/**
	 * 友好的方式显示时间
	 */
	public static String friendlyFormat(LocalDateTime date) {
		if (date == null)
			return "";
		LocalDateTime now = LocalDateTime.now();
		int days = now.toLocalDate().until(date.toLocalDate()).getDays();
		if (days >= 0) {
			if (days == 0) {
				int hour = now.getHour() - date.getHour();
				if (hour > 0)
					return date.format(TIME_NOSECOND);
				int minute = now.getMinute() - date.getMinute();
				if (minute < 2)
					return "刚刚";
				if (minute > 30)
					return "半小时前";
				return minute + "分钟前";
			} else if (days == 1) {
				return "昨天 " + date.format(TIME_NOSECOND);
			} else if (days == 2) {
				return "前天 " + date.format(TIME_NOSECOND);
			} else if (days <= 7) {
				return days + "天前";
			}
		}
		return date.format(DATE_TIME_FORMAT);
	}

}
