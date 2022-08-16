package com.cloud.cqc.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LocalDateUtil {

    public static String formatDate(Date date, String requiredDateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(requiredDateFormat);
        String outputDateFormatted = df.format(date);
        return outputDateFormatted;
    }

  /*  public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }*/


    /**
     * 两个日期之间相差的秒数
     *
     * @param startDate
     * @return
     */
    public static int calLastedTime(Date startDate) {
        long a = new Date().getTime();
        long b = startDate.getTime();
        int c = (int) ((a - b) / 1000);
        return c;
    }

    /**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of
     * December in the year 2002
     */
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th
     * day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    public static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;

    public static String[] TIME_RANGE_NAMES = {"今天", "本周", "上周", "本月", "上月",
            "今年", "全部"};
    public static String[] TIME_RANGE_VALUES = {"today", "thisweek",
            "lastweek", "thismonth", "lastmonth", "thisyear", "all"};

    /**
     * 暂时不用
     *
     * @param JD
     * @return
     */
    protected static final float normalizedJulian(float JD) {

        float f = Math.round(JD + 0.5f) - 0.5f;

        return f;
    }

    /**
     * 浮点值转换成日期格式<br>
     * 暂时不用 Returns the Date from a julian. The Julian date will be converted to
     * noon GMT, such that it matches the nearest half-integer (i.e., a julian
     * date of 1.4 gets changed to 1.5, and 0.9 gets changed to 0.5.)
     *
     * @param JD the Julian date
     * @return the Gregorian date
     */
    public static final Date toDate(float JD) {

        /*
         * To convert a Julian Day Number to a Gregorian date, assume that it is
         * for 0 hours, Greenwich time (so that it ends in 0.5). Do the
         * following calculations, again dropping the fractional part of all
         * multiplicatons and divisions. Note: This method will not give dates
         * accurately on the Gregorian Proleptic Calendar, i.e., the calendar
         * you get by extending the Gregorian calendar backwards to years
         * earlier than 1582. using the Gregorian leap year rules. In
         * particular, the method fails if Y<400.
         */
        float Z = (normalizedJulian(JD)) + 0.5f;
        float W = (int) ((Z - 1867216.25f) / 36524.25f);
        float X = (int) (W / 4f);
        float A = Z + 1 + W - X;
        float B = A + 1524;
        float C = (int) ((B - 122.1) / 365.25);
        float D = (int) (365.25f * C);
        float E = (int) ((B - D) / 30.6001);
        float F = (int) (30.6001f * E);
        int day = (int) (B - D - F);
        int month = (int) (E - 1);

        if (month > 12) {
            month = month - 12;
        }

        int year = (int) (C - 4715); // (if Month is January or February) or
        // C-4716 (otherwise)

        if (month > 2) {
            year--;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // damn 0 offsets
        c.set(Calendar.DATE, day);

        return c.getTime();
    }

    /**
     * Returns the days between two dates. Positive values indicate that the
     * second date is after the first, and negative values indicate, well, the
     * opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late  the "second date"
     * @return the days between the two dates
     */
    public static final int daysBetween(Date early, Date late) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);

        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that the
     * second date is after the first, and negative values indicate, well, the
     * opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static final int daysBetween(Calendar early, Calendar late) {

        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is based from
     * calculations found at <a
     * href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day
     * Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     *
     * @param c a calendar instance
     * @return the julian day number
     */
    public static final float toJulian(Calendar c) {

        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = C + D + E + F - 1524.5f;

        return JD;
    }

    /**
     * 暂时不用 Return a Julian date based on the input parameter. This is based
     * from calculations found at <a
     * href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day
     * Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     *
     * @param date
     * @return the julian day number
     */
    public static final float toJulian(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return toJulian(c);
    }

    /**
     * 日期增加
     *
     * @param isoString 日期字符串
     * @param fmt       格式
     * @param field     年/月/日 Calendar.YEAR/Calendar.MONTH/Calendar.DATE
     * @param amount    增加数量
     * @return
     */
    public static final String dateIncrease(String isoString, String fmt,
                                            int field, int amount) {

        try {
            Calendar cal = GregorianCalendar.getInstance(TimeZone
                    .getTimeZone("GMT"));
            cal.setTime(stringToDate(isoString, fmt, true));
            cal.add(field, amount);

            return dateToString(cal.getTime(), fmt);

        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Time Field Rolling function. Rolls (up/down) a single unit of time on the
     * given time field.
     *
     * @param isoString
     * @param field     the time field.
     * @param up        Indicates if rolling up or rolling down the field value. use
     *                  formating char's
     * @throws ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, String fmt, int field,
                                    boolean up) throws ParseException {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT"));
        cal.setTime(stringToDate(isoString, fmt));
        cal.roll(field, up);

        return dateToString(cal.getTime(), fmt);
    }

    /**
     * Time Field Rolling function. Rolls (up/down) a single unit of time on the
     * given time field.
     *
     * @param isoString
     * @param field     the time field.
     * @param up        Indicates if rolling up or rolling down the field value.
     * @throws ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, int field, boolean up)
            throws ParseException {

        return roll(isoString, DATETIME_PATTERN, field, up);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateText 字符串
     * @param format   日期格式
     * @param lenient  日期越界标志
     * @return
     */
    public static Date stringToDate(String dateText, String format,
                                    boolean lenient) {

        if (dateText == null) {

            return null;
        }

        if (dateText.length() == 10)
            dateText = dateText + " 00:00:00";

        DateFormat df = null;

        try {

            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }

            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            // df.setLenient(false);

            return df.parse(dateText);
        } catch (ParseException e) {

            return null;
        }
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     * @param format     日期格式
     * @return
     */
    public static Date stringToDate(String dateString, String format) {

        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     */
    public static Date stringToDate2(String dateString) {

        return stringToDate(dateString, DATETIME_PATTERN, LENIENT_DATE);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     */
    public static Date stringToDate(String dateString) {

        return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
    }

    /**
     * 根据时间变量返回时间字符串
     *
     * @param dateText 时间变量
     * @return 返回时间字符串
     */
    public static String stringToDateToString(String dateText) {
        if (dateText == null) {
            return null;
        }
        if (dateText.length() == 10)
            dateText = dateText + " 00:00:00";
        DateFormat df = null;
        try {
            df = new SimpleDateFormat(DATETIME_PATTERN);
            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(false);
            return dateToString(df.parse(dateText), DATETIME_PATTERN);
        } catch (ParseException e) {

            return null;
        }

    }

    /**
     * 根据时间变量返回时间字符串
     *
     * @param pattern 时间字符串样式
     * @param date    时间变量
     * @return 返回时间字符串
     */
    public static String dateToString(Date date, String pattern) {

        if (date == null) {

            return null;
        }

        try {

            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);

            return sfDate.format(date);
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateTimeToString(Date date) {
        return dateToString(date, DATETIME_PATTERN);
    }

    /**
     * 返回当前时间
     *
     * @return 返回当前时间
     */
    public static Date getCurrentDateTime() {
        Calendar calNow = Calendar.getInstance();
        Date dtNow = calNow.getTime();

        return dtNow;
    }

    /**
     * 返回当前日期字符串
     *
     * @param pattern 日期字符串样式
     * @return
     */
    public static String getCurrentDateString(String pattern) {
        return dateToString(getCurrentDateTime(), pattern);
    }

    /**
     * 返回当前日期字符串 yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentDateString() {
        return dateToString(getCurrentDateTime(), ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 返回当前日期+时间字符串 yyyy-MM-dd hh:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateToStringWithTime(Date date) {

        return dateToString(date, DATETIME_PATTERN);
    }

    /**
     * 日期增加-按日增加
     *
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByDay(Date date, int days) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    /**
     * 日期增加-按月增加
     *
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByMonth(Date date, int mnt) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);

        return cal.getTime();
    }

    /**
     * 日期增加-按年增加
     *
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByYear(Date date, int mnt) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.YEAR, mnt);

        return cal.getTime();
    }

    /**
     * 日期增加
     *
     * @param date 日期字符串 yyyy-MM-dd
     * @param days
     * @return 日期字符串 yyyy-MM-dd
     */
    public static String dateIncreaseByDay(String date, int days) {
        return dateIncreaseByDay(date, ISO_DATE_FORMAT, days);
    }

    /**
     * 日期增加
     *
     * @param date 日期字符串
     * @param fmt  日期格式
     * @param days
     * @return
     */
    public static String dateIncreaseByDay(String date, String fmt, int days) {
        return dateIncrease(date, fmt, Calendar.DATE, days);
    }

    /**
     * 日期字符串格式转换
     *
     * @param src    日期字符串
     * @param srcfmt 源日期格式
     * @param desfmt 目标日期格式
     * @return
     */
    public static String stringToString(String src, String srcfmt, String desfmt) {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }

    /**
     * 日期范围函数组 getBeginOfThisWeek 本周开始日期 getEndOfThisWeek 本周结束日期
     * getBeginOfLastWeek 上周开始日期 getEndOfLastWeek 上周结束日期 getBeginOfThisMonth
     * 本月开始日期 getEndOfThisMonth 本月结束日期 getBeginOfLastMonth 上月开始日期
     * getEndOfLastMonth 上月结束日期 getBeginOfThisYear 今年开始日期 getEndOfThisYear
     * 今年结束日期
     *
     * @return
     */
    public static Date getBeginOfThisWeek(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int todayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (todayOfWeek >= Calendar.MONDAY && todayOfWeek <= Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 2 - todayOfWeek);
        } else if (todayOfWeek == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -6);
        }

        return calendar.getTime();
    }

    public static Date getEndOfThisWeek(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int todayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (todayOfWeek >= Calendar.MONDAY && todayOfWeek <= Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 8 - todayOfWeek);
        } else if (todayOfWeek == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 0);
        }

        return calendar.getTime();
    }

    public static Date getBeginOfLastWeek(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -7);

        return getBeginOfThisWeek(calendar.getTime());
    }

    public static Date getEndOfLastWeek(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -7);

        return getEndOfThisWeek(calendar.getTime());
    }

    public static Date getBeginOfThisMonth(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, 1 - todayOfMonth);

        return calendar.getTime();
    }

    public static Date getEndOfThisMonth(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int maxOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DAY_OF_MONTH, maxOfMonth - todayOfMonth);

        return calendar.getTime();
    }

    public static Date getNextDayBegainDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getBeginOfLastMonth(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);

        return getBeginOfThisMonth(calendar.getTime());
    }

    public static Date getEndOfLastMonth(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);

        return getEndOfThisMonth(calendar.getTime());
    }

    public static Date getBeginOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);

        return stringToDate(thisYear + "-1-1");
    }

    public static Date getEndOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);

        return stringToDate(thisYear + "-12-31");
    }

    public static String getEndDate(String range) {
        if (range.trim().equals(TIME_RANGE_VALUES[0])) {// today
            return dateToString(new Date()) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[1])) {// this week
            return dateToString(getEndOfThisWeek(new Date())) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[2])) {// last week
            return dateToString(getEndOfLastWeek(new Date())) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[3])) {// this month
            return dateToString(getEndOfThisMonth(new Date())) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[4])) {// last month
            return dateToString(getEndOfLastMonth(new Date())) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[5])) {// this year
            return dateToString(getEndOfThisYear()) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[6])) {// all
            return "";
        }

        return "";
    }

    public static String getBeginDate(String range) {
        if (range.trim().equals(TIME_RANGE_VALUES[0])) {// today
            return dateToString(new Date()) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[1])) {// this week
            return dateToString(getBeginOfThisWeek(new Date())) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[2])) {// last week
            return dateToString(getBeginOfLastWeek(new Date())) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[3])) {// this month
            return dateToString(getBeginOfThisMonth(new Date())) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[4])) {// last month
            return dateToString(getBeginOfLastMonth(new Date())) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[5])) {// this year
            return dateToString(getBeginOfThisYear()) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[6])) {// all
            return "";
        }

        return "";
    }

    public static String getFormatDate(String formatDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN);
        String date = format.format(stringToDate(formatDate, DATETIME_PATTERN));
        return date;
    }

    /**
     * YYYYMMDDHHMMSS
     */
    public static String parseLongTime(Date date) {
        String fmt = "yyyyMMddHHmmss";
        DateFormat df = new SimpleDateFormat(fmt);
        String str = df.format(date);
        return str;
    }

    /**
     * YYYYMM
     */
    public static String getShortParseDate(Date date) {
        String fmt = "yyyyMM";
        DateFormat df = new SimpleDateFormat(fmt);
        String str = df.format(date);
        return str;
    }

    public static Date clearTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date fullTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }

    /**
     * 返回年份
     *
     * @param date 日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 返回月份
     *
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日份
     *
     * @param date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 对当前日期增加月份
     *
     * @param month
     * @return
     */
    public static Date addMonth(Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 输入两个日期中区间的所有点
     */

    static String dateFormat = "yyyy-MM-dd";
    static SimpleDateFormat format = new SimpleDateFormat(dateFormat);

    private static Date str2Date(String str) {
        if (str == null)
            return null;

        try {
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取小时间隔之后的时间
     *
     * @param date：时间点
     * @param intervalHour：小时差
     * @return：yyyy-MM-dd HH:mm:ss
     */
    public static String getHourInterval(Date date, int intervalHour) {
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(Calendar.HOUR_OF_DAY, intervalHour);
        return dateToString(Cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    //当天的开始
    public static Date TodayBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //当天的结束
    public static Date TodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date addDayOfMonth(Date date, int day) {

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        c.add(Calendar.DAY_OF_MONTH, day);

        return c.getTime();

    }

    public static Date addDateMinut(Date date, int hour) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        // System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return stringToDate(format.format(date), "yyyy-MM-dd HH:mm:ss");

    }

    public static final String FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_DAY = "yyyy-MM-dd";

    /**
     * String 转 Date
     **/
    public static Date stringTransDate(String formatString, String dateString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            return format.parse(dateString);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Date 转 String
     **/
    public static String dateTransString(String formatString, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            return format.format(date);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 根据开始结束时间 返回完整的连续的时间集合(字符串格式)
     **/
    public static List<String> getDateListBy(String startDateStr, String endDateStr) {

        List<String> dateList = new ArrayList<String>();

        Date startTime = stringTransDate(FORMAT_DAY, startDateStr);

        Date endTime = stringTransDate(FORMAT_DAY, endDateStr);

        int timeSpan = datesBetween(startTime, endTime);

        for (int i = 0; i <= timeSpan; i++) {

            Date nextDay = dateIncreaseByDay(startTime, i);

            dateList.add(dateToString(nextDay, FORMAT_DAY));
        }
        return dateList;
    }

    /**
     * 计算两个日期之间相差的天数
     */
    public static int datesBetween(Date startTime, Date endTime) {
        long betweenDays = getTimeInMillisBetween(startTime, endTime) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public static String dateToWeek(String datetime) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date date = parseAsStandardDateFormat(datetime);
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public static Date parseAsStandardDateFormat(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Long getTimeInMillisBetween(Date StartTime, Date endTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);
            StartTime = sdf.parse(sdf.format(StartTime));
            endTime = sdf.parse(sdf.format(endTime));
            Calendar cal = Calendar.getInstance();
            cal.setTime(StartTime);
            long time1 = cal.getTimeInMillis();
            cal.setTime(endTime);
            long time2 = cal.getTimeInMillis();
            return time2 - time1;
        } catch (ParseException p) {
            p.printStackTrace();
        }
        return 0L;
    }

    public static Date dateFormat(String formatString, Date date) {
        String dateStr = dateTransString(formatString, date);
        return stringTransDate(formatString, dateStr);
    }

}
