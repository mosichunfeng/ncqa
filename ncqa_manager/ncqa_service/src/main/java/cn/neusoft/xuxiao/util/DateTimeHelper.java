package cn.neusoft.xuxiao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期时间类型的工具类，提供对日期和时间基于毫秒及以上精度的格式化方法、
 * 运算方法、比较方法等。注意:大部分对日期对象(java.cn.neusoft.xuxiao.util.Date)操作的方法,
 * 不会修改原日期对象,而以一个新的日期对象返回。
 * <p>
 * <p>
 * <p><pre><b>
 * 历史更新记录:</b>
 * 2004-6-17  创建此类型
 * 2005-9-30  修改方法：parseToDate(),去掉了对年份从(1970-9999)这个范围的限制。抛出异常:IllegalArgumentException(),JThinkRuntimeException()
 * 2005-10-2  修改方法: 其它方法,抛出异常:IllegalArgumentException(),JThinkRuntimeException()
 * 2005-10-2  增加方法: addTime(Date d, double times, int type)
 * 2005-10-2  增加方法: addSystemTime()
 * 2005-10-2  修改方法, 对于所有以日期对象类型java.cn.neusoft.xuxiao.util.Date为参数的方法,如果日期为null,都抛出异常:IllegalArgumentException()
 * <p>
 * </pre></p>
 */

public final class DateTimeHelper {

    /**
     * 把时间格式化成如：2002-08-03 8:26:30.400 am 格式的字符串
     */
    public final static String FMT_yyyyMMddHHmmssSa_12 = "yyyy-MM-dd KK:mm:ss.S a";

    /**
     * 把时间格式化成如：2002-08-03 8:26:16 am 格式的字符串
     */
    public final static String FMT_yyyyMMddHHmmssa_12 = "yyyy-MM-dd KK:mm:ss a";

    /**
     * 把时间格式化成如：2002-08-03 8:26 am 格式的字符串
     */
    public final static String FMT_yyyyMMddHHmma_12 = "yyyy-MM-dd KK:mm a";

    /**
     * 把时间格式化成如：2002-08-03 8 am 格式的字符串
     */
    public final static String FMT_yyyyMMddHHa_12 = "yyyy-MM-dd KK a";

    /**
     * 把时间格式化成如：2002-07-05 am 格式的字符串
     */
    public final static String FMT_yyyyMMdda_12 = "yyyy-MM-dd a";

    /**
     * 把时间格式化成如：2002-08-03 08:26:30.400 格式的字符串
     */
    public final static String FMT_yyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 把时间格式化成如：2002-08-03 08:26:16 格式的字符串
     */
    public final static String FMT_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 把时间格式化成如：2002-08-03 08:26 格式的字符串
     */
    public final static String FMT_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";

    /**
     * 把时间格式化成如：2002-08-03 08 格式的字符串
     */
    public final static String FMT_yyyyMMddHH = "yyyy-MM-dd HH";

    /**
     * 把时间格式化成如：2002-07-05 格式的字符串
     */
    public final static String FMT_yyyyMMdd = "yyyy-MM-dd";

    /**
     * 把时间格式化成如：12:08 PM(下午) 格式的字符串
     */
    public final static String FMT_HHmmA_12 = "KK:mm a";

    /**
     * 把时间格式化成如：0:55 AM上午, CST 格式的字符串
     */
    public final static String FMT_HHmmAz_12 = "KK:mm a,z";

    /**
     * 把时间格式化成如：0:56 AM上午, 中国标准时间 格式的字符串
     */
    public final static String FMT_HHmmAzzzz_12 = "KK:mm a,zzzz";

    /**
     * 把时间格式化成如：12:08:23 am 格式的字符串
     */
    public final static String FMT_HHmmssA_12 = "KK:mm:ss a";

    /**
     * 把时间格式化成如：0:55:33 AM上午, CST 格式的字符串
     */
    public final static String FMT_HHmmssAz_12 = "KK:mm:ss a,z";

    /**
     * 把时间格式化成如：0:56:23 AM上午, 中国标准时间 格式的字符串
     */
    public final static String FMT_HHmmssAzzzz_12 = "KK:mm:ss a,zzzz";

    /**
     * 把时间格式化成如：22:04:45 格式的字符串
     */
    public final static String FMT_HHmmss = "HH:mm:ss";

    /**
     * 把时间格式化成如：22:04:45.824 格式的字符串
     */
    public final static String FMT_HHmmssS = "HH:mm:ss.S";

    /**
     * 把时间格式化成如：22:04 格式的字符串
     */
    public final static String FMT_HHmm = "HH:mm";

    /**
     * 把时间格式化成如：22:04,CST 格式的字符串
     */
    public final static String FMT_HHmmz = "HH:mm,z";

    /**
     * 把时间格式化成如：22:04,中国标准时间 格式的字符串
     */
    public final static String FMT_HHmmzzzz = "HH:mm,zzzz";

    /**
     * 把时间格式化成如：Sun,Nov 14,'2004 格式的字符串
     */
    public final static String FMT_WWMMDDYY_EN = "EEE,MMM d,''yyyy";

    /**
     * 把时间格式化成如：星期日,2004年十一月14号 格式的字符串
     */
    public final static String FMT_WWMMDDYY_CN = "EEE,yyyy年MMMd号";

    /**
     * 把时间格式化成如：Sun,Nov 14,'2004 格式的字符串
     */
    public final static String FMT_MMDDYY_EN = "MMM d,''yyyy";

    /**
     * 把时间格式化成如：星期日,2004年十一月14号 格式的字符串
     */
    public final static String FMT_MMDDYY_CN = "yyyy年MMMd号";


    public final static String FMT_mmDDYY_SN = "yyyy年MM月dd号";

    /**
     * 把时间格式化成如：星期几 格式的字符串,即可获得该日这个时间是星期几
     */
    public final static String FMT_WW = "EEE";

    public final static String FMT_ddMMyyyy = "dd-MM-yyyy";

    /**
     * 把时间格式成日期数字，如：20120606
     */
    public final static String FMT_yyyyMMdd_noseparator = "yyyyMMdd";

    /**
     * 把时间格式成日期数字，如：20120606121212
     */
    public final static String FMT_yyyyMMddhhmmss_noseparator = "yyyyMMddHHmmss";
    /**
     * 常用的格式化时间的格式组，用于本类中格式化字符串成时间型
     */
    private final static String[] formatStr = {
            FMT_ddMMyyyy,
            FMT_yyyyMMddHHmmssS,
            FMT_yyyyMMddHHmmss,
            FMT_yyyyMMddHHmm,
            FMT_yyyyMMddHH,
            FMT_yyyyMMdd,
            FMT_mmDDYY_SN,
            FMT_HHmmss,
            FMT_HHmmssS,
            FMT_HHmm,
            FMT_HHmmz,
            FMT_HHmmzzzz,
            FMT_yyyyMMddHHmmssSa_12,
            FMT_yyyyMMddHHmmssa_12,
            FMT_yyyyMMddHHmma_12,
            FMT_yyyyMMddHHa_12,
            FMT_yyyyMMdda_12,
            FMT_HHmmA_12,
            FMT_HHmmAz_12,
            FMT_HHmmAzzzz_12,
            FMT_HHmmssA_12,
            FMT_HHmmssAz_12,
            FMT_HHmmssAzzzz_12
    };


    /**
     * 私有化构造器，使得不能产生该类对象，类中所有的方法均为静态方法
     */
    private DateTimeHelper() {
    }

    /**
     * 根据给出的Date值和格式串采用操作系统的默认所在的国家风格来格式化时间，并返回相应的字符串
     *
     * @param date
     * @param formatStr
     * @return 如果为null，返回字符串""
     */
    public static String formatDateTimetoString(Date date, String formatStr) {
        String reStr = "";
        if (date == null || formatStr == null || formatStr.trim().length() < 1) {
            return reStr;
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(formatStr);
        reStr = sdf.format(date);
        return reStr;
    }

    public static Date getSystemDate(String fmtstr) {
        try {
            return parseToDate(formatDateTimetoString(getSystemDate(), fmtstr));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return getSystemDate();
        }
    }

    /**
     * 根据给出的Date值和格式串采用给定的国家所在的国家风格来格式化时间，并返回相应的字符串
     *
     * @param date      日期对象
     * @param formatStr 日期格式
     * @return 如果为null，返回字符串""
     */
    public static String formatDateTimetoString(Date date, String formatStr, Locale locale) {
        String reStr = "";
        if (date == null || formatStr == null || locale == null || formatStr.trim().length() < 1) {
            return reStr;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr, locale);
        reStr = sdf.format(date);
        return reStr;
    }

    /**
     * 根据给出的Date值字符串和格式串采用操作系统的默认所在的国家风格来格式化时间，并返回相应的字符串
     *
     * @param dateStr   日期串
     * @param formatStr 日期格式
     * @return 如果为null，返回""
     * @throws Exception
     */
    public static String formatDateTimetoString(String dateStr, String formatStr) throws Exception {
        String dStr = "";
        if (dateStr != null && dateStr.trim().length() > 0 && formatStr != null && formatStr.trim().length() > 0) {
            dStr = formatDateTimetoString(parseToDate(dateStr), formatStr);
        }
        return dStr;
    }

    /**
     * 根据给出的Date值字符串和格式串采用指定国家的风格来格式化时间，并返回相应的字符串
     *
     * @param dateStr   日期串
     * @param formatStr 日期格式
     * @return 如果为null，返回""
     * @throws Exception
     */
    public static String formatDateTimetoString(String dateStr, String formatStr, Locale locale) throws Exception {
        String dStr = "";
        if (dateStr != null && dateStr.trim().length() > 0 && formatStr != null && formatStr.trim().length() > 0 && locale != null) {
            dStr = formatDateTimetoString(parseToDate(dateStr, locale), formatStr, locale);
        }
        return dStr;
    }

    /**
     * 按指定的格式和操作系统默认国家的风格把给定的日期字符串格式化为一个Date型日期
     *
     * @param dateTimeStr
     * @param formatStr
     * @return java.cn.neusoft.xuxiao.util.Date类型对象
     * @throws Exception
     */
    public static Date parseToDate(String dateTimeStr, String formatStr) throws Exception {
        if (dateTimeStr == null || formatStr == null || dateTimeStr.trim().length() < 1 || formatStr.trim().length() < 1) {
            throw new IllegalArgumentException("参数dateTimeStr、formatStr不能是null或空格串!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        try {
            return sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            throw new Exception(e);
        }
    }

    /**
     * 按指定的格式和指定国家的风格把给定的日期字符串格式化为一个Date型日期
     *
     * @param dateTimeStr
     * @param formatStr
     * @param locale
     * @return java.cn.neusoft.xuxiao.util.Date类型对象
     * @throws Exception
     */
    public static Date parseToDate(String dateTimeStr, String formatStr, Locale locale) throws Exception {
        if (dateTimeStr != null && formatStr != null && locale != null && dateTimeStr.trim().length() > 0 && formatStr.trim().length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr, locale);
            try {
                return sdf.parse(dateTimeStr);
            } catch (ParseException e) {
                throw new Exception(e);
            }
        } else {
            throw new IllegalArgumentException("参数dateTimeStr、formatStr、locale不能是null或空格串!");
        }

    }

    /**
     * 按操作系统默认国家的风格把给定的日期字符串格式化为一个Date型日期
     *
     * @param dateTimeStr
     * @return java.cn.neusoft.xuxiao.util.Date类型对象
     * @throws Exception
     */
    public static Date parseToDate(String dateTimeStr) throws Exception {
        if (dateTimeStr == null || dateTimeStr.trim().length() < 1) {
            throw new IllegalArgumentException("参数dateTimeSt不能是null或空格串!");
        }
        int formatStrLength = formatStr.length;
        int i = 0;

        for (i = 0; i < formatStrLength; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr[i]);
            try {
                return sdf.parse(dateTimeStr);
            } catch (ParseException e) {
            }
        }
        throw new Exception("日期格式不正确!");
    }

    /**
     * 根据给出的年月和日返回一个日期型的对象
     *
     * @param year  年
     * @param month 月 ，1到12
     * @param day   日 ，1到31
     * @return java.cn.neusoft.xuxiao.util.Date类型对象
     * @throws Exception
     */
    public static Date parseToDate(int year, int month, int day) throws Exception {
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            throw new IllegalArgumentException("参数不正确!");
        }
        String yearStr = String.valueOf(year);
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);

        return parseToDate(yearStr + "-" + monthStr + "-" + dayStr);

    }

    /**
     * 根据给出的年月日、时分秒、返回一个对应的Date型对象
     *
     * @param year  年
     * @param month 月 ，1到12
     * @param day   日 ，1到31
     * @param h     小时，从0到23
     * @param m     分，从0到60
     * @param s     秒，从0到60
     * @return java.cn.neusoft.xuxiao.util.Date类型对象
     * @throws Exception
     */
    public static Date parseToDate(int year, int month, int day, int h, int m, int s) throws Exception {
        if (month < 1 || month > 12 || day < 1 || day > 31 || h < 0 || h > 23 || m < 0 || m > 60 || s < 0 || s > 60) {
            throw new IllegalArgumentException("参数不正确!");
        }
        String yearStr = String.valueOf(year);
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);
        String hStr = String.valueOf(h);
        String mStr = String.valueOf(m);
        String sStr = String.valueOf(s);

        return parseToDate(yearStr + "-" + monthStr + "-" + dayStr + " " + hStr + ":" + mStr + ":" + sStr);


    }

    /**
     * 按指定国家的风格把给定的日期字符串格式化为一个Date型日期
     *
     * @param dateTimeStr
     * @return java.cn.neusoft.xuxiao.util.Date类型对象
     * @throws Exception
     */
    public static Date parseToDate(String dateTimeStr, Locale locale) throws Exception {
        if (dateTimeStr == null || dateTimeStr.trim().length() < 1 || locale == null) {
            throw new IllegalArgumentException("参数dateTimeSt、locale不能是null或空格串!");
        }
        int formatStrLength = formatStr.length;
        int i = 0;
        for (i = 0; i < formatStrLength; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr[i], locale);
            try {
                return sdf.parse(dateTimeStr);
            } catch (ParseException e) {
            }
        }
        throw new Exception("日期格式不正确!");
    }

    /**
     * 将给定的日期时间字符串按操作系统默认的国家风格格式化成"yyyy-MM-dd HH:mm:ss"格式的日期时间串;
     *
     * @param dateTimeStr
     * @return 如果为null，返回""
     * @throws Exception
     */
    public static String formatDateTimetoString(String dateTimeStr) throws Exception {
        return formatDateTimetoString(dateTimeStr, FMT_yyyyMMddHHmmss);
    }

    /**
     * 将给定的日期时间字符串按指定国家的风格格式化成"yyyy-MM-dd HH:mm:ss"格式的日期时间串;
     *
     * @param dateTimeStr
     * @param locale
     * @return 如果为null，返回""
     * @throws Exception
     */
    public static String formatDateTimetoString(String dateTimeStr, Locale locale) throws Exception {
        return formatDateTimetoString(dateTimeStr, FMT_yyyyMMddHHmmss, locale);
    }

    /**
     * 将给定的日期时间按操作系统默认的国家内格格式化成"yyyy-MM-dd HH:mm:ss"格式的日期时间串;
     *
     * @param dateTime
     * @return 如果为null，返回""
     */
    public static String formatDateTimetoString(Date dateTime) {
        return formatDateTimetoString(dateTime, FMT_yyyyMMddHHmmss);
    }

    /**
     * 将给定的日期时间按指定国家的风格格式化成"yyyy-MM-dd HH:mm:ss"格式的日期时间串;
     *
     * @param dateTime
     * @param locale
     * @return 如果为null，返回""
     */
    public static String formatDateTimetoString(Date dateTime, Locale locale) {
        return formatDateTimetoString(dateTime, FMT_yyyyMMddHHmmss, locale);
    }

    /**
     * 将给定的日期字符串按操作系统默认的国家风格格式化成"yyyy-MM-dd"格式的日期串;
     *
     * @param dateStr 日期串
     * @return 如果为null，返回""
     * @throws Exception
     */
    public static String formatDatetoString(String dateStr) throws Exception {
        return formatDateTimetoString(dateStr, FMT_yyyyMMdd);
    }

    /**
     * 将给定的日期字符串按指定国家的风格格式化成"yyyy-MM-dd"格式的日期串;
     *
     * @param dateStr 日期串
     * @param locale  Locale
     * @return 如果为null，返回""
     * @throws Exception
     */
    public static String formatDatetoString(String dateStr, Locale locale) throws Exception {
        return formatDateTimetoString(dateStr, FMT_yyyyMMdd, locale);
    }

    /**
     * 将给定的日期按指定操作系统默认国家的风格格式化成"yyyy-MM-dd"格式的日期串;
     *
     * @param d 日期对象
     * @return 如果为null，返回""
     */
    public static String formatDatetoString(Date d) {
        return formatDateTimetoString(d, FMT_yyyyMMdd);
    }

    /**
     * 将给定的日期按指定国家的风格格式化成"yyyy-MM-dd"格式的日期串;
     *
     * @param d      日期对象
     * @param locale Locale
     * @return 如果为null，返回""
     */
    public static String formatDatetoString(Date d, Locale locale) {
        return formatDateTimetoString(d, FMT_yyyyMMdd, locale);
    }

    /**
     * 将给定的日期时间字符串按操作系统默认的国家风格格式化成"HH:mm:ss"格式的时间串;
     *
     * @param dateTimeStr
     * @return 如果为null，返回""
     * @throws Exception
     */
    public static String formatTimetoString(String dateTimeStr) throws Exception {
        return formatDateTimetoString(dateTimeStr, FMT_HHmmss);
    }

    /**
     * 将给定的日期时间字符串按指定国家的风格格式化成"HH:mm:ss"格式的时间串;
     *
     * @param dateTimeStr
     * @param locale
     * @return 如果为null，返回""
     * @throws Exception
     */
    public static String formatTimetoString(String dateTimeStr, Locale locale) throws Exception {
        return formatDateTimetoString(dateTimeStr, FMT_HHmmss, locale);
    }

    /**
     * 将给定的日期时间按指定操作系统默认国家的风格格式化成"HH:mm:ss"格式的时间串;
     *
     * @param dateTimeStr
     * @return 如果为null，返回""
     */
    public static String formatTimetoString(Date dateTimeStr) {
        return formatDateTimetoString(dateTimeStr, FMT_HHmmss);
    }

    /**
     * 将给定的日期时间按指定国家的风格格式化成"HH:mm:ss"格式的时间串;
     *
     * @param dateTimeStr
     * @param locale
     * @return 如果为null，返回""
     */
    public static String formatTimetoString(Date dateTimeStr, Locale locale) {
        return formatDateTimetoString(dateTimeStr, FMT_HHmmss, locale);
    }

    /**
     * 返回一个时间的年份整数
     *
     * @param d
     * @return 年份
     */
    public static int getYearOfDate(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回一个时间的月份整数
     *
     * @param d
     * @return 月份
     */
    public static int getMonthOfYear(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回一个时间的天份整数，是这个月的第几天
     *
     * @param d
     * @return 天份
     */
    public static int getDayOfMonth(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回一个时间的天份整数，是这个年份的第几天
     *
     * @param d
     * @return 天份
     */
    public static int getDayOfYear(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 返回一个时间的天份整数，是这个周的第几天
     *
     * @param d
     * @return 天份
     */
    public static int getDayOfWeek(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 返回一个时间的周的整数，是这个月的第几周
     *
     * @param d
     * @return 周
     */
    public static int getWeekOfMonth(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 返回一个时间的周的整数，是这个年份的第几周
     *
     * @param d
     * @return 周
     */
    public static int getWeekOfYear(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 返回该时间所对应的在一天中的小时数的整数，如当前(Date now)是下午3点，返回为15
     *
     * @param d
     * @return 小时
     */
    public static int getHoursOfDay(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        return hours;
    }

    /**
     * 返回该时间所对应的在一天中的小时数的整数(采用12小时制)，如当前(Date now)是下午3点，返回为3
     *
     * @param d
     * @return 小时
     */
    public static int getHoursOfDay12(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int hours = calendar.get(Calendar.HOUR);
        return hours;
    }


    /**
     * 返回该时间所对应的分钟数中的整数，如now是15点14分，则返回14
     *
     * @param d
     * @return 分钟
     */
    public static int getMinutesOfHour(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int minutes = calendar.get(Calendar.MINUTE);

        return minutes;
    }

    /**
     * 返回该时间所对应的秒数中的整数,如now是15点14分34秒，则返回34
     *
     * @param d
     * @return 秒
     */
    public static int getSecondsOfMinute(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int seconds = calendar.get(Calendar.SECOND);

        return seconds;
    }

    /**
     * 返回该时间所对应的毫秒数中的整数,如now是15点14分34秒470毫秒，则返回470
     *
     * @param d
     * @return 毫秒
     */
    public static int getMillisecondsOfSecond(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        return millisecond;
    }

    /**
     * 返回该时间相对于1970年1月1日开始计算的对应的毫秒数
     *
     * @param d
     * @return 毫秒数
     */
    public static long getTime(Date d) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        return d.getTime();
    }

    /**
     * 比较两个时间的先后顺序。
     * 如果时间d1在d2之前，返回-1,
     * 如果时间d1在d2之后，返回1，
     * 如果时间d1在d2之后，返回1，
     * 如果二者相等，返回0
     *
     * @param d1
     * @param d2
     * @return 如果时间d1在d2之前，返回-1,
     * 如果时间d1在d2之后，返回1，
     * 如果二者相等，返回0
     */
    public static int compareTwoDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象!");
        }

        long dI1 = d1.getTime();
        long dI2 = d2.getTime();

        if (dI1 < dI2) {
            return -1;
        } else if (dI1 > dI2) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 返回两个日期之间的毫秒数的差距
     *
     * @param d1
     * @param d2
     * @return 二者至1970年1.1后的毫秒数的差值
     */
    public static long getMillisecondsOfTwoDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象!");
        }
        long dI1 = d1.getTime();
        long dI2 = d2.getTime();
        return (dI1 - dI2);
    }

    /**
     * 获得两个日期之间相差的秒数
     *
     * @param d1
     * @param d2
     * @return 两日期之间相差的秒数
     */
    public static double getSecondsOfTwoDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象!");
        }
        long i = getMillisecondsOfTwoDate(d1, d2);

        return (double) i / 1000;
    }

    /**
     * 获得两个日期之间相差的分钟数
     *
     * @param d1
     * @param d2
     * @return 两日期之间相差的分钟数
     */
    public static double getMinutesOfTwoDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象!");
        }
        long millions = getMillisecondsOfTwoDate(d1, d2);
        return (double) millions / 60 / 1000;
    }

    /**
     * 获得两个日期之间相差的小时数
     *
     * @param d1
     * @param d2
     * @return 两日期之间相差的小时数
     */
    public static double getHoursOfTwoDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象!");
        }
        long millions = getMillisecondsOfTwoDate(d1, d2);
        return (double) millions / 60 / 60 / 1000;
    }

    /**
     * 获得两个日期之间相差的天数
     *
     * @param d1
     * @param d2
     * @return 两日期之间相差的天数
     */
    public static double getDaysOfTwoDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("参数d1或d2不能是null对象!");
        }
        long millions = getMillisecondsOfTwoDate(d1, d2);
        return (double) millions / 24 / 60 / 60 / 1000;
    }


    /**
     * 把给定的时间加上指定的时间值，可以为负。
     *
     * @param d     日期对象
     * @param times 时间值
     * @param type  类型，
     *              Calendar.MILLISECOND,毫秒<BR>
     *              Calendar.SECOND,秒<BR>
     *              Calendar.MINUTE,分钟<BR>
     *              Calendar.HOUR,小时<BR>
     *              Calendar.DATE,日<BR>
     * @return 如果d为null，返回null
     */
    public static Date addTime(Date d, double times, int type) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        long qv = 1;
        switch (type) {
            case Calendar.MILLISECOND:
                qv = 1;
                break;
            case Calendar.SECOND:
                qv = 1000;
                break;
            case Calendar.MINUTE:
                qv = 1000 * 60;
                break;
            case Calendar.HOUR:
                qv = 1000 * 60 * 60;
                break;
            case Calendar.DATE:
                qv = 1000 * 60 * 60 * 24;
                break;
            default:
                throw new RuntimeException("时间类型不正确！type＝" + type);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        long milliseconds = Math.round(Math.abs(times) * qv);
        if (times > 0) {
            for (; milliseconds > 0; milliseconds -= 2147483647) {
                if (milliseconds > 2147483647) {
                    calendar.add(Calendar.MILLISECOND, 2147483647);
                } else {
                    calendar.add(Calendar.MILLISECOND, (int) milliseconds);
                }
            }
        } else {
            for (; milliseconds > 0; milliseconds -= 2147483647) {
                if (milliseconds > 2147483647) {
                    calendar.add(Calendar.MILLISECOND, -2147483647);
                } else {
                    calendar.add(Calendar.MILLISECOND, -(int) milliseconds);
                }
            }
        }
        return calendar.getTime();
    }


    /**
     * 把给定的时间加上指定的年份，可以为负, 返回新的被加上了年份的日期对象,不影响参数日期对象值
     *
     * @param d
     * @param years
     * @return 日期对象
     */
    public static Date addYears(Date d, int years) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }


    /**
     * 把给定的时间加上指定的月份，可以为负
     *
     * @param d
     * @param months
     * @return 日期对象
     */
    public static Date addMonths(Date d, int months) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * 把给定的时间加上指定的天数，可以为负
     *
     * @param d    日期对象
     * @param days
     * @return 日期对象
     */
    public static Date addDays(Date d, int days) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, days * 24);
        return calendar.getTime();
    }

    /**
     * 把给定的时间加上指定的小时，可以为负
     *
     * @param d     日期对象
     * @param hours
     * @return 日期对象
     */
    public static Date addHours(Date d, int hours) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    /**
     * 把给定的时间加上指定的分钟，可以为负
     *
     * @param d
     * @param minutes
     * @return 日期对象
     */
    public static Date addMinutes(Date d, int minutes) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 把给定的时间加上指定的秒数，可以为负
     *
     * @param d
     * @param seconds
     * @return 日期对象
     */
    public static Date addSeconds(Date d, int seconds) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 把给定的时间加上指定的毫秒数，可以为负
     *
     * @param d
     * @param milliseconds
     * @return 日期对象
     */
    public static Date addMilliseconds(Date d, int milliseconds) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MILLISECOND, milliseconds);
        return calendar.getTime();
    }

    /**
     * 设置一个日期对象的年份是新的给定的年份
     *
     * @param d    需要设定的日期对象
     * @param year 新的年份
     * @return 日期对象
     */
    public static Date setYearOfDate(Date d, int year) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 设置一个日期对象的月份是新的给定的月份
     *
     * @param d     需要设定的日期对象
     * @param month 新的月份
     * @return 新日期对象
     */
    public static Date setMonthOfDate(Date d, int month) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 设置一个日期对象的天是新的给定的天
     *
     * @param d   需要设定的日期对象
     * @param day 新的天
     * @return 新日期对象
     */
    public static Date setDayOfDate(Date d, int day) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 设置一个日期对象的小时是新的给定的小时
     *
     * @param d    需要设定的日期对象
     * @param hour 新的小时数
     * @return 新日期对象
     */
    public static Date setHourOfDate(Date d, int hour) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 设置一个日期对象的分钟是新的给定的分钟数
     *
     * @param d      需要设定的日期对象
     * @param minute 新的分钟数
     * @return 新日期对象
     */
    public static Date setMinuteOfDate(Date d, int minute) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 设置一个日期对象的秒数是新的给定的分钟数
     *
     * @param d      需要设定的日期对象
     * @param second 新的秒数
     * @return 新日期对象
     */
    public static Date setSecondOfDate(Date d, int second) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 设置一个日期对象的毫秒数是新的给定的分钟数
     *
     * @param d           需要设定的日期对象
     * @param millisecond 新的毫秒数
     * @return 新日期对象
     */
    public static Date setMillisecondOfDate(Date d, int millisecond) {
        if (d == null) {
            throw new IllegalArgumentException("参数d不能是null对象!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }


    /**
     * 返回指定日期的月份的天数量
     *
     * @param d 日期对象
     */
    public static int getDaysOfMonth(Date d) {
        int year = getYearOfDate(d);
        int month = getMonthOfYear(d);
        return getDaysOfMonth(year, month);
    }

    /**
     * 返回指定日期的月份的天数量
     *
     * @param year  年
     * @param month 月
     */
    public static int getDaysOfMonth(int year, int month) {
        int days = 0;
        if (month == 2) {
            if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                days = 29;
            } else {
                days = 28;
            }
        }
        if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            days = 30;
        }
        if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
            days = 31;
        }
        return days;
    }


    /**
     * 返回系统时间,以日期对象形式返回
     *
     * @return 日期对象
     */
    public static Date getSystemDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 返回系统时间,以毫秒形式返回
     *
     * @return 毫秒数
     */
    public static long getSystemTime() {
        return System.currentTimeMillis();
    }

    /**
     * 返回24小时前的时间
     *
     * @param date
     * @return
     */
    public static Date getLastDay(Date date) {
        long day = date.getTime();
        long lastDay = day - 24 * 60 * 60 * 1000;
        return new Date(lastDay);
    }

    /**
     * 返回24小时后的时间
     *
     * @param date
     * @return
     */
    public static Date getTomorrow(Date date) {
        long day = date.getTime();
        long tomorrow = day + 24 * 60 * 60 * 1000;
        return new Date(tomorrow);
    }

    /**
     * 取得30天前的这个时间
     *
     * @param
     * @return
     */
    public static Date getDayLastMonth() {
        long day = new Date().getTime();
        long dayLastMonth = day - 24 * 60 * 60 * 1000 * 20;
        return new Date(dayLastMonth);
    }

    /**
     * 取得30天后的这个时间
     *
     * @param
     * @return
     */
    public static Date getDayNextMonth() {
        long day = new Date().getTime();
        long dayNextMonth = day + 20 * 24 * 60 * 60 * 1000;
        return new Date(dayNextMonth);
    }

    public static int getMonthCount(Date sDate, Date eDate) {
        String sDateStr = DateTimeHelper.formatDateTimetoString(sDate, "MM");
        String eDateStr = DateTimeHelper.formatDateTimetoString(eDate, "MM");
        int monthCount = Integer.parseInt(eDateStr) - Integer.parseInt(sDateStr) + 1;
        return monthCount;
    }

    /**
     * 取得下个月的这天，比如2月1日可取得3月1日，此方法有很大局限性，不能用于月末的天数
     *
     * @param date
     * @return
     */
    public static Date getDayNextMonth(Date date) {
        String yearStr = DateTimeHelper.formatDateTimetoString(date, "yyyy");
        String monthStr = DateTimeHelper.formatDateTimetoString(date, "MM");
        String dayStr = DateTimeHelper.formatDateTimetoString(date, "dd");
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        if (month == 12) {
            month = 1;
            year = year + 1;
            yearStr = String.valueOf(year);
            monthStr = String.valueOf(month);
        }

        String dateStr = yearStr + "-" + monthStr + "-" + dayStr;
        try {
            date = DateTimeHelper.parseToDate(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 获取当月开始时0点0分0秒
     *
     * @return
     */
    public static Date getCurrentMouthStart() {
        Date d = getSystemDate();
        d = setDayOfDate(d, 1);
        d = setHourOfDate(d, 0);
        d = setMinuteOfDate(d, 0);
        d = setSecondOfDate(d, 0);
        return d;
    }

    /**
     * 返回下月的这天
     *
     * @param date
     * @return
     */
    public static Date getDateNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, +1);
        return cal.getTime();
    }

    /**
     * 根据时间获取和当前时间的差距.
     *
     * @param date - 待比较实际
     * @return 时间差距字符串
     */
    public static String getPassdTime(Date date) {
        if (null == date) {
            return null;
        }
        String ret = null;

        // 计算和当前相差秒数
        long currentTimeMillis = System.currentTimeMillis();
        long timeDiff = (currentTimeMillis - date.getTime());

        int seconds = (int) (timeDiff / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        int months = days / 30;

        if (months != 0) {
            ret = months + "个月前";
        } else if (days != 0) {
            ret = days + "天前";
        } else if (hours != 0) {
            ret = hours + "小时前";
        } else if (minutes != 0) {
            ret = minutes + "分钟前";
        } else {
            ret = "刚刚";
        }

        return ret;
    }

    /**
     * 获取当前日期后几天的日期
     *
     * @param day 天数
     * @return
     */
    public static Date getCurrentNextDays(int day) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, day);
        Date date = calendar.getTime();
        System.out.println(sdf.format(date));
        return date;
    }

    public static String getCurrentStringDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(new Date());
        return dateString;
    }

    /**
     * 生成时间戳字符串
     *
     * @return
     */
    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    public static String getTimeStampNum() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /***
     * 通过秒数计算长度
     * @return
     */
    public static String getStringDate(Integer d) {
        String dateString = null;
        Integer m = d / 60;
        Integer s = d % 60;
        if (s < 10) {
            dateString = m.toString() + ":0" + s.toString();
        } else {
            dateString = m.toString() + ":" + s.toString();
        }
        return dateString;
    }

    /**
     * 获取本周一
     *
     * @return
     */
    public static String getCurrentMonday() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.add(Calendar.DATE, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return monday;
    }

    /**
     * 获取本周末
     *
     * @return
     */
    public static String getCurrentSunday() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.add(Calendar.DATE, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return sunday;
    }

    /**
     * 获取上周一
     *
     * @return
     */
    public static String getLastMonday() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return monday;
    }

    /**
     * 获取上周一
     *
     * @return
     */
    public static Date getLastMondayForDate() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获取上周末
     *
     * @return
     */
    public static String getLastSunday() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return sunday;
    }

    /**
     * 获取上周末
     *
     * @return
     */
    public static Date getLastSundayForDate() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * 获取上月第一天
     *
     * @return
     */
    public static String getfirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayofMonth = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return firstDayofMonth;
    }

    /**
     * 获取指定月的上月第一天
     *
     * @return
     */
    public static Date getfirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取上月第一天
     *
     * @return
     */
    public static Date getfirstDayOfMonth(Integer flag) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取上月最后一天
     *
     * @return
     */
    public static String getlastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        String lastDayofMonth = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return lastDayofMonth;
    }

    /**
     * 获取上月最后一天
     *
     * @return
     */
    public static Date getlastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取本月第一天
     *
     * @return
     */
    public static String getCurrentFirstDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return first;
    }

    /**
     * 获取制定月月第一天
     *
     * @return
     */
    public static Date getCurrentFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @return
     */
    public static String getCurrentLastDayOfMonth() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime());
        return last;
    }

    /**
     * 获取本月最后一天
     *
     * @return
     */
    public static Date getCurrentLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param d1 较小的时间
     * @param d2 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static long getDifference(Date d1, Date d2, int field) {
        Calendar ca1 = Calendar.getInstance();
        ca1.setTime(d1);
        Calendar ca2 = Calendar.getInstance();
        ca2.setTime(d2);

        switch (field) {
            case Calendar.YEAR:
                return ca1.get(field) - ca2.get(field);
            case Calendar.MONTH:
                return ca1.get(field) - ca2.get(field);
            case Calendar.DATE:
                ca1.set(Calendar.HOUR_OF_DAY, 0);
                ca1.set(Calendar.MINUTE, 0);
                ca1.set(Calendar.SECOND, 0);
                ca1.set(Calendar.MILLISECOND, 0);

                ca2.set(Calendar.HOUR_OF_DAY, 0);
                ca2.set(Calendar.MINUTE, 0);
                ca2.set(Calendar.SECOND, 0);
                ca2.set(Calendar.MILLISECOND, 0);

                long diff = ca1.getTimeInMillis() - ca2.getTimeInMillis();
                return diff / (24 * 60 * 60 * 1000);
        }

        return 0;
    }

    /**
     * 获取一个时间的这周某天的日期
     *
     * @param time 时间
     * @param week 星期几  1——7
     * @return
     */
    public static Date getWeekByDate(Date time, Integer week) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        if (week > 1) {
            cal.add(Calendar.DATE, week - 1);
        }
        return cal.getTime();

    }

    /**
     * 获取上一个时间的这周某天的日期
     *
     * @param time 时间
     * @param week 星期几  1——7
     * @return
     */
    public static Date getWeekByDate(Date time, Integer week, Integer last) {


        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DAY_OF_MONTH, -7);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        if (week > 1) {
            cal.add(Calendar.DATE, week - 1);
        }
        return cal.getTime();

    }

    /**
     * 获取某个日期的月初 或者月末
     *
     * @param time 时间
     * @param type 月初 first  月末 last
     * @return
     */
    public static Date getMonthByDate(Date time, String type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MONTH, 0);
        if ("first".equals(type)) {
            cal.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return cal.getTime();
    }

    /**
     * 获取某个日期的月初 或者月末
     *
     * @param time 时间
     * @param type 月初 first  月末 last
     * @return
     */
    public static Date getMonthByDate(Date time, String type, Integer last) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MONTH, -1);
        if ("first".equals(type)) {
            cal.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return cal.getTime();
    }

    /**
     * 获取某个日期的年初 或者年末
     *
     * @param time 时间
     * @param type 年初 first  年末 last
     * @return
     */
    public static Date getYearByDate(Date time, String type) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.YEAR, 0);
        if ("first".equals(type)) {
            cal.set(Calendar.DAY_OF_YEAR, 1);
        } else {
            cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
        }
        return cal.getTime();
    }


    public static Date setDateField(Date time, int hours, int min, int seconds) {
        time = time == null ? new Date() : time;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, seconds);
        return calendar.getTime();
    }


    public static Date dateFortimestamp(long timestamp, String format) {
        if (0 == timestamp) {
            timestamp = System.currentTimeMillis();
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return strToDate(dateFormat.format(new Date(timestamp)), format);
    }


    public static Date strToDate(String dataStr, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date parse = dateFormat.parse(dataStr);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            instance.set(Calendar.SECOND, 0);
            instance.set(Calendar.MILLISECOND, 0);
            return instance.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }


    public static Date strToDate2(String dataStr, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date parse = dateFormat.parse(dataStr);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            instance.set(Calendar.SECOND, 0);
            instance.set(Calendar.MILLISECOND, 0);
            instance.set(Calendar.MINUTE, 0);
            instance.set(Calendar.HOUR, 0);
            return instance.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 求两个日期之间的所有月份
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getMonth(Date startTime, Date endTime) {

        List<String> months = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM");

        long sM1 = strToDate(dateFormat.format(startTime), "yyyy-MM").getTime();
        long eM1 = strToDate(dateFormat.format(endTime), "yyyy-MM").getTime();
        if (sM1 > eM1) {
            return months;
        }
        while (true) {
            long sM = strToDate(dateFormat.format(startTime), "yyyy-MM").getTime();
            long eM = strToDate(dateFormat.format(endTime), "yyyy-MM").getTime();
            if (sM > eM) {
                break;
            }
            months.add(dateFormat1.format(startTime));
            startTime = addMonths(startTime, 1);
        }
        return months;
    }


    public static Date formatDate(Date date, String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return strToDate(format1.format(date), format);
    }

    public static Date formatDate2(Date date, String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return strToDate2(format1.format(date), format);
    }


    public static long countDateDays(Date start, Date end) {
        Date date = formatDate(start, "yyyy-MM-dd");
        Date date2 = formatDate(end, "yyyy-MM-dd");
        return (date.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
    }


    /**
     * 获取指定日期所在周的第一天和最后一天
     *
     * @param dataStr
     * @return
     * @throws ParseException
     */
    public static Map<String, Date> getFirstAndLastOfWeek(String dataStr) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dataStr));
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        Date data1 = cal.getTime();
        cal.add(Calendar.DAY_OF_WEEK, 6);
        // 所在周结束日期
        Date data2 = cal.getTime();
        Map<String, Date> result = new HashMap<String, Date>();
        result.put("startTime", data1);
        result.put("endTime", data2);
        return result;
    }

    /**
     * 获取指定日期所在周的第一天和最后一天
     *
     * @return
     * @throws ParseException
     */
    public static Map<String, Date> getFirstAndLastOfWeek() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return getFirstAndLastOfWeek(format.format(new Date()));
    }


    /**
     * 获取两个日期之间的日期
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    public static List<String> getBetweenDates(String start, String end) throws Exception {

        if (CommonUtil.isEmpty(start, end)) {
            // 如果其中一个为null 则默认当前时间月的所有天数
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cale = Calendar.getInstance();
            cale.setTime(new Date());
            cale.add(Calendar.MONTH, 0);
            cale.set(Calendar.DAY_OF_MONTH, 1);
            start = format.format(cale.getTime());
            // 获取前月的最后一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
            end = format.format(cale.getTime());
        }

        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(sdf.parse(start));

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(sdf.parse(end));
        tempEnd.add(Calendar.DAY_OF_MONTH, 1);

        Calendar curr = tempStart;
        while (curr.before(tempEnd)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * 获取两个日期之间的日期
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    public static List<String> getBetweenDates(Date start, Date end) throws Exception {
        return getBetweenDates(formatDateTimetoString(start, FMT_yyyyMMdd), formatDateTimetoString(end, FMT_yyyyMMdd));
    }


    /**
     * 获取指定日期的周时间集合
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static List<String> getWeekDate(Date date) throws Exception {
        Map<String, Date> week = getFirstAndLastOfWeek(new SimpleDateFormat("yyyy-MM-dd").format(date));
        return getBetweenDates(formatDateTimetoString(week.get("startTime")), formatDateTimetoString(week.get("endTime")));
    }


    public static List<String> getWeekDate() throws Exception {
        return getWeekDate(new Date());
    }


    /**
     * 获取当前日期月份里的所有日期
     *
     * @param date
     * @return
     */
    public static List<String> getMonthDate(Date date) throws Exception {
        Date first = getCurrentFirstDayOfMonth(date);
        Date last = getCurrentLastDayOfMonth(date);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return getBetweenDates(f.format(first), f.format(last));
    }


    public static List<String> getMonthDate() throws Exception {
        return getMonthDate(new Date());
    }


    public static void main(String[] args) throws Exception {


        System.out.println(formatDate(new Date(), FMT_yyyyMMdd).getTime());

    }


}