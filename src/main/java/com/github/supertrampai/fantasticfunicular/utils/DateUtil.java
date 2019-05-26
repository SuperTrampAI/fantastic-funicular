package com.github.supertrampai.fantasticfunicular.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    private static final String DATE = "yyyy-MM-dd";
    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final String STIME = "HH:mm";
    private static final String DATETIME2 = "yyyyMMddHHmmss";

    public static String DateToStr(Date date, String pattern) {
        if (pattern == null) {
            pattern = DATETIME;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String NowStr(String pattern) {
        if (pattern == null) {
            pattern = DATETIME;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(Calendar.getInstance().getTime());
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.DateToStr(new Date(), "yyyy"));

    }

    //转换字符串为日期时间(yyyy-MM-dd HH:mm:ss)
    public static Date getDateTimeByString(String dtstring) {
        return getDate(dtstring, DATETIME);
    }

    //转换字符串为日期(yyyy-MM-dd)
    public static Date getDateByString(String dtstring) {
        return getDate(dtstring, DATE);
    }

    //转换字符串为指定格式日期时间
    public static Date getDate(String dtstring, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = df.parse(dtstring);
        } catch (Exception e) {
        }
        return d;
    }

    public static boolean isTuesday() {
//        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY;
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
    }

    //转换日期为字符串(yyyy-MM-dd HH:mm:ss)
    public static String getStringByDateTime(Date date) {
        return DateToStr(date, DATETIME);
    }

    //转换日期为字符串(yyyy-MM-dd)
    public static String getStringByDate(Date date) {
        return DateToStr(date, DATE);
    }

    //转换日期为字符串(HH:mm)
    public static String getStringByStime(Date date) {
        return DateToStr(date, STIME);
    }

    //转换日期为字符串(yyyyMMddHHmmss)
    public static String getStringByDateTime2(Date date) {
        return DateToStr(date, DATETIME2);
    }

//    //转换日期时间为指定格式字符串
//    public static String formatDateTime(Date myDate, String pattern) {
//        DateFormat fd = new SimpleDateFormat(pattern);
//        return fd.format(myDate);
//    }

    //计算日期（向前或向后计算days天，允许跨月跨年）
    public static Date addDay(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    //计算日期（向前或向后计算days天，允许跨月跨年）
    public static Date addHour(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    public static Date addMinute(Date date, Integer minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    //计算日期（向前或向后计算days天，允许跨月跨年）(yyyy-MM-dd)
    public static String addDay(String dtstring, int days) {
        return getStringByDate(addDay(getDateByString(dtstring), days));
    }

    /**
     * 计算日期（向前或向后计算月份）
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 根据时间获取月份的第一天.
     *
     * @param date
     * @return
     */
    public static Date getFristDayByDate(Date date) {
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        return getDateByString(getStringByDate(gcLast.getTime()));
    }

    /**
     * 计算两个时间相差的分钟数.
     *
     * @param sDate
     * @param eDate
     * @return
     */
    public static double calculateMintues(Date sDate, Date eDate) {
        return Math.abs((sDate.getTime() - eDate.getTime()) / (1000 * 60 * 1.0));
    }

    /**
     * 计算两个日期相差的小时数.
     *
     * @param sDate
     * @param eDate
     * @return
     */
    public static long calculateHours(Date sDate, Date eDate) {
        return (sDate.getTime() - eDate.getTime()) / (1000 * 60 * 60);
    }

    /**
     * 计算两个日期相差的天数.
     *
     * @param sDate
     * @param eDate
     * @return
     */
    public static long calculateDays(Date sDate, Date eDate) {
        return (sDate.getTime() - eDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param sDate
     * @param eDate
     * @return
     */
    public static double calculateDaysAndHours(Date sDate, Date eDate) {
        return (double) (sDate.getTime() - eDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    //转换字符串为日期(yyyy-MM-dd)
    public static Date transDate(Date date) {
        String dateStr = getStringByDate(date);
        return getDateByString(dateStr);
    }

    /***
     * 两个日期相差多少秒
     *
     * @param sDate
     * @param eDate
     * @return
     */
    public static int getTimeDelta(Date sDate, Date eDate) {
        long timeDelta = (sDate.getTime() - eDate.getTime()) / 1000;//单位是秒
        int secondsDelta = timeDelta > 0 ? (int) timeDelta : (int) Math.abs(timeDelta);
        return secondsDelta;
    }


    /***
     * 两个日期相差多少秒
     * @param dateStr1  :yyyy-MM-dd HH:mm:ss
     * @param dateStr2 :yyyy-MM-dd HH:mm:ss
     */
    public static int getTimeDelta(String dateStr1, String dateStr2) {
        Date date1 = getDateTimeByString(dateStr1);
        Date date2 = getDateTimeByString(dateStr2);
        return getTimeDelta(date1, date2);
    }

    // -------------------------------------
    /**
     * 构造函数.
     *
     */
    public DateUtil() {
        throw new RuntimeException("this is a util class,can not instance!");
    }

    /**
     * 添加字段注释.
     */
    public static final String ENUM_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 添加字段注释.
     */
    public static final String ENUM_FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 添加字段注释.
     */
    public static final String ENUM_FORMAT_YMDS = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 添加字段注释.
     */
    public static final String ENUM_FORMAT_SLASH = "yyyy/MM/dd HH:mm:ss";

    /**
     * 添加字段注释.
     */
    public static final String ENUM_FORMAT_YMDS_SLASH = "yyyy/MM/dd HH:mm:ss.S";

    /**
     * 添加字段注释.
     */
    public static final String LEVEL_DAY = "day"; // 粒度级别

    /**
     * 添加字段注释.
     */
    public static final String LEVEL_HOUR = "hour";

    /**
     * 添加字段注释.
     */
    public static final String LEVEL_MINUTE = "minute";

    /**
     * 添加字段注释.
     */
    public static final String LEVEL_SECOND = "second";

    /**
     * 日期特殊字符对应.
     */
    private static Map<String, String> mapSign = new HashMap<>();

    /**
     * 使用ThreadLocal保证SimpleDateFormat线程安全.
     */
    private static ThreadLocal<Map<String, SimpleDateFormat>> threadLocalDateFormat = new ThreadLocal<>();

    /**
     * 初始化DateFormat标志位.
     *
     */
    private static void initMapSign() {
        if (mapSign.isEmpty()) {
            mapSign.put("上午|下午", "a");
            mapSign.put("星期[一二三四五六日天七]", "E");
            mapSign.put("CST", "z");
            mapSign.put("公元[前]?", "G");
        }
    }

    /**
     * 常规日期格式yyyy-MM-dd HH:mm:ss.
     *
     * @param date
     *            date
     * @return time
     */
    public static String format(Date date) {
        return getDateFormat(ENUM_FORMAT).format(date);
    }

    /**
     * 常规日期格式yyyy-MM-dd.
     *
     * @param date
     *            date
     * @return time
     */
    public static String formatYMd(Date date) {
        return getDateFormat(ENUM_FORMAT_YMD).format(date);
    }

    /**
     * 格式化时间.
     *
     * @param date
     *            date
     * @param dateFormat
     *            dateFormat
     * @return time
     */
    public static String format(Date date, String dateFormat) {
        if (null == date) {
            return null;
        }
        return getDateFormat(dateFormat).format(date);
    }

    /**
     * parse时间(yyyy-MM-dd HH:mm:ss).
     *
     * @param source
     *            source
     * @return Date
     * @throws ParseException
     *             ParseException
     */
    public static Date parse(String source) throws ParseException {
        return getDateFormat(ENUM_FORMAT).parse(source);
    }

    /**
     * parse时间(yyyy-MM-dd).
     *
     * @param source
     *            source
     * @return Date
     * @throws ParseException
     *             ParseException
     */
    public static Date parseYMd(String source) throws ParseException {
        return getDateFormat(ENUM_FORMAT_YMD).parse(source);
    }

    /**
     * 格式化时间.
     *
     * @param time
     *            time
     * @param dateFormat
     *            dateFormat
     * @return Date
     * @throws ParseException
     *             ParseException
     */
    public static Date parse(String time, String dateFormat) throws ParseException {
        if (isNullOrEmpty(time) || isNullOrEmpty(dateFormat)) {
            return null;
        }
        return getDateFormat(dateFormat).parse(time);
    }

    /**
     * 获取指定时间格式的 SimpleDateFormat.
     *
     * @param pattern
     *            时间格式
     *
     * @return SimpleDateFormat
     */
    private static SimpleDateFormat getDateFormat(String pattern) {
        Map<String, SimpleDateFormat> dateFormatMap = threadLocalDateFormat.get();
        if (dateFormatMap == null) {
            dateFormatMap = new HashMap<>();
        }
        SimpleDateFormat simpleDateFormat = dateFormatMap.get(pattern);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            dateFormatMap.put(pattern, simpleDateFormat);
            threadLocalDateFormat.set(dateFormatMap);
        }
        return simpleDateFormat;
    }

    /**
     * 自动解析时间格式并parse（时间格式为yyyyMMddHHmmssS，默认24小时制、前包含且必须包含yyyyMMdd）.
     *
     * @param time
     *            time
     * @return Date
     * @throws ParseException
     *             ParseException
     */
    public static Date parseAuto(String time) throws ParseException {
        if (isNullOrEmpty(time) || time.length() < 8) {
            return null;
        }
        initMapSign();
        time = time.trim();
        String formatPattern = "";
        if (time.matches("[\\d]+")) { // 纯数字
            String all = "yyyyMMddHHmmssSSS";
            if (time.length() > all.length()) { // 超长截取
                time = time.substring(0, all.length());
            }
            formatPattern = all.substring(0, time.length());
        } else {
            char next = 'y';
            String idNext = "yMdHmsS";
            StringBuilder buffer = new StringBuilder();
            for (char var : time.toCharArray()) {
                if (String.valueOf(var).matches("[0-9]")) {
                    buffer.append(next);
                } else if ("T".equals(String.valueOf(var))) {
                    buffer.append("'").append(var).append("'");
                } else {
                    buffer.append(var);
                    next = idNext.charAt(Math.min(idNext.indexOf(next) + 1, idNext.length() - 1));
                }
            }
            formatPattern = buffer.toString();
        }
        for (Entry<String, String> entry : mapSign.entrySet()) {
            formatPattern = formatPattern.replaceAll(entry.getKey(), entry.getValue());
        }
        return parse(time, formatPattern);
    }

    /**
     * 是否为空或"".
     *
     * @param param
     *            param
     * @return boolean
     */
    private static boolean isNullOrEmpty(String param) {
        return null == param || "".equals(param.trim());
    }

    /**
     * 日期增加num天.
     *
     * @param date
     *            date
     * @param num
     *            加减天数
     * @return Date
     */
    public static Date addDate(Date date, int num) {
        return addDate(date, Calendar.DATE, num);
    }

    /**
     * 时间增加.
     *
     * @param date
     *            date
     * @param calendar
     *            加减级别Calendar
     * @param num
     *            加减天数
     * @return Date
     */
    public static Date addDate(Date date, int calendar, int num) {
        if (null == date || 0 == num) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendar, num);
        return cal.getTime();
    }

    /**
     * 保留日期到某一级别（天、时、分、秒...）.
     *
     * @param date
     *            date
     * @param level
     *            保留级别，null保留到day
     * @return date
     */
    public static Date setDate(Date date, String level) {
        if (null == date || null == level) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (level) {
            case LEVEL_DAY: // 保留到 Day
                // cal.set(Calendar.HOUR, 0); // 12小时制
                cal.set(Calendar.HOUR_OF_DAY, 0); // 24小时制
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                break;
            case LEVEL_HOUR: // 保留到 Hour
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                break;
            case LEVEL_MINUTE: // 保留到 MINUTE
                cal.set(Calendar.SECOND, 0);
                break;
            case LEVEL_SECOND: // 保留到 SECOND
                cal.set(Calendar.MILLISECOND, 0);
                break;
            default:
                break;
        }
        return cal.getTime();
    }

    /**
     * 比较两个日期的间隔（时间差绝对值,向下取整）（day、hour、minute）.
     *
     * @param date1
     *            date1
     * @param date2
     *            date2
     * @param level
     *            比较级别
     * @return int 无对应时间间隔级别
     */
    public static Integer getDateInterval(Date date1, Date date2, String level) {
        Double num = dateInterval(date1, date2, level);
        if (null == num) {
            return null;
        }
        return (int) Math.floor(num);
    }

    /**
     * 比较两个日期的间隔（时间差绝对值,向上取整）（day、hour、minute）.
     *
     * @param date1
     *            date1
     * @param date2
     *            date2
     * @param level
     *            比较级别
     * @return int 无对应时间间隔级别
     */
    public static Integer getDateIntervalCeil(Date date1, Date date2, String level) {
        Double num = dateInterval(date1, date2, level);
        if (null == num) {
            return null;
        }
        return (int) Math.ceil(num);
    }

    /**
     * 比较两个日期的间隔（day、hour、minute）.
     *
     * @param date1
     *            date1
     * @param date2
     *            date2
     * @param level
     *            比较级别
     * @return int 无对应时间间隔级别
     */
    private static Double dateInterval(Date date1, Date date2, String level) {
        Double time = (double) (date1.getTime() - date2.getTime());
        if (time < 0) {
            time = time * -1;
        }
        Double num = null;
        switch (level) {
            case LEVEL_DAY: // 天
                num = (Double) (time / TimeUnit.DAYS.toMillis(1));
                break;
            case LEVEL_HOUR: // 小时
                num = (Double) (time / TimeUnit.HOURS.toMillis(1));
                break;
            case LEVEL_MINUTE: // 分钟
                num = (Double) (time / TimeUnit.MINUTES.toMillis(1));
                break;
            case LEVEL_SECOND: // 秒
                num = (Double) (time / TimeUnit.SECONDS.toMillis(1));
                break;
            default:
                break;
        }
        return num;
    }

    /**
     * 获取当前日期指定时间.
     *
     * @param date
     *            date
     *
     * @param time
     *            time
     * @return date
     * @throws ParseException
     *             ParseException
     */
    public static Date dateToHms(Date date, String time) throws ParseException {
        if (null == date || isNullOrEmpty(time)) {
            return date;
        }
        StringBuilder timeBuf = new StringBuilder();
        String dateStr = formatYMd(date);
        timeBuf.append(dateStr).append(" ");
        time = time.trim();
        while (!time.matches(".*\\d")) {
            time = time.substring(0, time.length() - 1);
        }
        timeBuf.append(time);
        if (time.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            timeBuf.append(".0");
        } else if (time.matches("\\d{1,2}:\\d{1,2}")) {
            timeBuf.append(":00.0");
        } else if (time.matches("\\d{1,2}")) {
            timeBuf.append(":00:00.0");
        }
        return parse(timeBuf.toString(), ENUM_FORMAT_YMDS);
    }

    /**
     * 将Date类转换为XMLGregorianCalendar.
     *
     * @param date
     *            date
     * @return XMLGregorianCalendar
     * @throws DatatypeConfigurationException
     *             DatatypeConfigurationException
     */
    public static XMLGregorianCalendar dateToXmlDate(Date date) throws DatatypeConfigurationException {
        XMLGregorianCalendar dateType = null;
        if (null != date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            DatatypeFactory dtf = DatatypeFactory.newInstance();
            dateType = dtf.newXMLGregorianCalendar();
            if (null != dateType) {
                dateType.setYear(cal.get(Calendar.YEAR));
                // 由于Calendar.MONTH取值范围为0~11,需要加1
                dateType.setMonth(cal.get(Calendar.MONTH) + 1);
                dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
                dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
                dateType.setMinute(cal.get(Calendar.MINUTE));
                dateType.setSecond(cal.get(Calendar.SECOND));
            }
        }
        return dateType;
    }

    /**
     * 清理ThreadLocal（每次线程结束都应执行此操作）.
     *
     */
    public static void clearThreadLocal() {
        threadLocalDateFormat.remove();
    }

}
