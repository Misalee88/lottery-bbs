package com.zhegu.core.frameworkcore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 时间操作工具类
 * </p>
 * <p>
 * create: 2011-1-20 下午04:57:31
 * </p>
 *
 * @author 张良[zhang.l1@haihangyun.com]
 * @version 1.0
 */
public class DateUtils {

    /**
     * 时间偏移量
     */
    private static final int TIME_ZONE_OFFSET = Calendar.getInstance().getTimeZone().getRawOffset();

    /**
     * 时间格式化：年月日时分秒
     */
    public static final String SDF_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式全串
     */
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.sss";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY = "yyyy";

    public static final String MM = "MM";

    public static final String DD = "dd";

    public static final String HH = "HH";

    public static final String MI = "mm";

    public static final String SS = "ss";


    /**
     * <p>
     * 解析时间串
     * </p>
     *
     * @param dateStr
     * @return Date
     */
    public static Date parse(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        String format = DATE_FORMAT_FULL.substring(0, dateStr.length());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <p>
     * 解析时间串
     * </p>
     *
     * @param dateStr
     * @param format
     * @return Date
     */
    public static Date parse(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }
        if (format == null) {
            try {
                return new SimpleDateFormat(YYYY_MM_DD).parse(dateStr);
            } catch (ParseException e) {
            }
        }
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return Date
     */
    public static Date toDate(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(YYYY_MM_DD).parse(new SimpleDateFormat(YYYY_MM_DD).format(date));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * <p>
     * 格式化时间为字符串
     * </p>
     *
     * @param date
     * @return String
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        String dateStr = new SimpleDateFormat(SDF_YMDHMS).format(date);
        return dateStr.replaceAll("((\\s|:)00)+$", "");
    }

    /**
     * <p>
     * 格式化时间为字符串
     * </p>
     *
     * @param date
     * @param format
     * @return String
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (format == null) {
            return format(date);
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * <p>
     * 天数比较
     * </p>
     *
     * @param date1
     * @param date2
     * @return int 返回代码
     * <ul>
     * <li>1 : date1在date2之前</li>
     * <li>-1 : date1在date2之后</li>
     * <li>0 : date1和date2同一天</li>
     * <li>-2 : date1或date2有一个为null</li>
     * </ul>
     */
    public static int compareForDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return -2;
        }
        int offset = getDayOffset(date1, date2);
        if (offset > 0) {
            return 1;
        }
        if (offset < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * <p>
     * 获取相差天数
     * </p>
     *
     * @param begin 起始日期
     * @param end   终止日期
     * @return int 天数
     */
    public static int getDayOffset(Date begin, Date end) {
        if (begin == null || end == null) {
            return 0;
        }
        long day1 = (begin.getTime() + TIME_ZONE_OFFSET) / 86400000;
        long day2 = (end.getTime() + TIME_ZONE_OFFSET) / 86400000;
        return (int) (day2 - day1);
    }

    /**
     * <p>
     * 获取当日日期
     * </p>
     *
     * @return date
     */
    public static Date getCurrentDay() {
        try {
            String date = new SimpleDateFormat(YYYY_MM_DD).format(new Date());
            return new SimpleDateFormat(YYYY_MM_DD).parse(date);
        } catch (ParseException e) {
            long tm = System.currentTimeMillis();
            tm = tm - (tm + TIME_ZONE_OFFSET) % 86400000;
            return new Date(tm);
        }
    }

    /**
     * 获取年初日期
     *
     * @return Date
     */
    public static Date getFirstDateOfYear() {
        try {
            return new SimpleDateFormat(YYYY_MM_DD).parse(Calendar.getInstance().get(Calendar.YEAR) + "-01-01");
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 年度第一天的指定时间日期
     *
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static String getFirstDateOfYear(Date date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return format(calendar.getTime(), "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 获取年初日期
     *
     * @return Date
     */
    public static Date getFirstDateOfYear(Date date) {
        try {
            Calendar cal = Calendar.getInstance();
            if (date != null) {
                cal.setTime(date);
            }
            return new SimpleDateFormat(YYYY_MM_DD).parse(Calendar.getInstance().get(Calendar.YEAR) + "-01-01");
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 获取年初日期
     *
     * @param date
     * @return
     * @author ZHIQIANG LI
     */
    public static String getFirstDateStrOfYear(Date date) {
        try {
            Calendar cal = Calendar.getInstance();
            if (date != null) {
                cal.setTime(date);
            }
            Date temp = new SimpleDateFormat(YYYY_MM_DD).parse(Calendar.getInstance().get(Calendar.YEAR) + "-01-01");
            return format(temp, YYYY_MM_DD);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 获取年初日期
     *
     * @param dateStr
     * @return
     * @author ZHIQIANG LI
     */
    public static String getFirstDateStrOfYear(String dateStr) {
        return getFirstDateStrOfYear(parse(dateStr, YYYY_MM_DD));
    }

    /**
     * 获取年初日期
     *
     * @param dateStr
     * @return
     * @author ZHIQIANG LI
     */
    public static Date getFirstDateOfYear(String dateStr) {
        Date date = parse(dateStr, YYYY_MM_DD);
        return getFirstDateOfYear(date);
    }

    /**
     * <p>
     * 获取指定日期下一个月的第一天
     * </p>
     *
     * @param date
     * @return Date
     */
    public static Date getFirstDayOfNextMonth(Date date) {
        if (date == null) {
            return null;
        }
        String monthStr = new SimpleDateFormat(YYYY_MM).format(date);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat(YYYY_MM_DD).parse(monthStr + "-01"));
            cal.add(Calendar.MONTH, 1);
            return cal.getTime();
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * <p>
     * 获取指定日期的第一天
     * </p>
     *
     * @param date
     * @return Date
     */
    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        String monthStr = new SimpleDateFormat(YYYY_MM).format(date);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat(YYYY_MM_DD).parse(monthStr + "-01"));
            return cal.getTime();
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * <p>
     * 获取指定日期的最后一天
     * </p>
     *
     * @param date
     * @return Date
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        String monthStr = new SimpleDateFormat(YYYY_MM).format(date);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat(YYYY_MM_DD).parse(monthStr + "-01"));
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            return cal.getTime();
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * <p>
     * 获取指定日期的下个月第一天
     * </p>
     *
     * @param date
     * @return Date
     */
    public static Date getLastDayOfNextMonth(Date date) {
        if (date == null) {
            return null;
        }
        String monthStr = new SimpleDateFormat(YYYY_MM).format(date);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat(YYYY_MM_DD).parse(monthStr + "-01"));
            cal.add(Calendar.MONTH, 1);
            return cal.getTime();
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * <p>
     * 修改时间
     * </p>
     *
     * @param date   需要修改的时间
     * @param type   时间类型
     * @param offset 偏移量
     * @return Date
     */
    public static Date add(Date date, int type, int offset) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, offset);
        return cal.getTime();
    }

    /**
     * <p>
     * 修改时间
     * </p>
     *
     * @param time   时间戳
     * @param type   时间常量类型
     * @param offset 偏移数
     * @return Date
     */
    public static Date add(long time, int type, int offset) {
        Date date = new Date(time);
        return add(date, type, offset);
    }

    /**
     * <p>
     * 获取相差小时数
     * </p>
     *
     * @param begin 起始日期
     * @param end   终止日期
     * @return int 小时
     */
    public static int getHourOffset(Date begin, Date end) {
        if (begin == null || end == null) {
            return 0;
        }
        long hour1 = (begin.getTime() + TIME_ZONE_OFFSET) / 3600000;
        long hour2 = (end.getTime() + TIME_ZONE_OFFSET) / 3600000;
        return (int) (hour2 - hour1);
    }

    /**
     * <p>
     * 获取相差分钟数
     * </p>
     *
     * @param begin 起始日期
     * @param end   终止日期
     * @return int 分钟
     */
    public static int getMinuteOffset(Date begin, Date end) {
        if (begin == null || end == null) {
            return 0;
        }
        long minute1 = (begin.getTime() + TIME_ZONE_OFFSET) / 60000;
        long minute2 = (end.getTime() + TIME_ZONE_OFFSET) / 60000;
        return (int) (minute2 - minute1);
    }

    /**
     * <p>
     * 获取时间显示
     * </p>
     *
     * @param date
     * @return
     * @date 2015年5月2日
     */
    public static String getDateText(Date date, String format) {
        if (date == null)
            return "";
        String hm = new SimpleDateFormat(" HH:mm").format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        if (sdf.format(date).equals(sdf.format(cal.getTime()))) {
            return "今天" + hm;
        }
        cal.add(Calendar.DAY_OF_MONTH, -1);
        if (sdf.format(date).equals(sdf.format(cal.getTime()))) {
            return "昨天" + hm;
        }
        cal.add(Calendar.DAY_OF_MONTH, -1);
        if (sdf.format(date).equals(sdf.format(cal.getTime()))) {
            return "前天" + hm;
        }
        if (format == null)
            format = "MM-dd";
        return new SimpleDateFormat(format).format(date) + hm;
    }

    /**
     * 根据日期返回该日期近三年年份
     *
     * @param date 日期 格式 yyyy-MM-dd
     * @param num
     * @return Map<String,String>
     * @author ZHIQIANG LI
     */
    public static Map<String, String> getYears(String date, int num) {

        int year = Integer.valueOf(date.split("-")[0]);

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < num; i++) {
            map.put(String.valueOf(year - i), String.valueOf(year - i));
        }

        return map;
    }

    /**
     * 根据日期返回该日期近三年年份
     *
     * @param date 日期
     * @return Map<String,String>
     * @author ZHIQIANG LI
     */
    public static Map<String, String> getYears(Date date, int num) {
        String str = new SimpleDateFormat(YYYY_MM_DD).format(date);
        return getYears(str, num);
    }

    /**
     * 获取当前日期指定时间点
     *
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     */
    public static Date getPreviousSpecifiedTime(int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前日期指定时间点
     *
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     */
    public static Date getCurrentSpecifiedTime(int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前日期的第二天指定时间点
     *
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     */
    public static Date getNextSpecifiedTime(int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取BIM项目所需的起始时间
     *
     * @return
     */
    public static Date getStartDateOfBIM(Date currentTime) {

//		Date currentTime = new Date();
        //获取当日的5点和第二天的5点作为起始时间和结束时间
        Date startDate = DateUtils.getCurrentSpecifiedTime(5, 0, 0);
        Date endDate = DateUtils.getNextSpecifiedTime(5, 0, 0);
        //如果当前时间小于当日的5点，查询应从昨天的5点为开始时间，当前时间为结束时间
        if (currentTime.getTime() < startDate.getTime()) {
            startDate = DateUtils.getPreviousSpecifiedTime(5, 0, 0);
        }
        return startDate;
    }

    /**
     * 获取BIM项目所需的起始时间
     *
     * @return
     */
    public static Date getStartDateOfBIM(String dateStr, String formatStr) {
        Date currentTime = parse(dateStr, formatStr);
        return getStartDateOfBIM(currentTime);
    }

    /**
     * 获取BIM项目所需的起始时间
     *
     * @return
     */
    public static String getStartDateStrOfBIM(Date currentTime, String formatStr) {
        Date endDate = getStartDateOfBIM(currentTime);
        return format(endDate, formatStr);
    }

    /**
     * 获取BIM项目所需的起始时间
     *
     * @return
     */
    public static String getStartDateStrOfBIM(String dateStr, String formatStr) {
        Date endDate = getStartDateOfBIM(dateStr, formatStr);
        return format(endDate, formatStr);
    }

    /**
     * 获取BIM项目所需的截止时间
     *
     * @return
     */
    public static Date getEndDateOfBIM(Date currentTime) {

//		Date currentTime = new Date();
        //获取当日的5点和第二天的5点作为起始时间和结束时间
        Date startDate = DateUtils.getCurrentSpecifiedTime(5, 0, 0);
        Date endDate = DateUtils.getNextSpecifiedTime(5, 0, 0);
        //如果当前时间小于当日的5点，查询应从昨天的5点为开始时间，当前时间为结束时间
        if (currentTime.getTime() < startDate.getTime()) {
            endDate = startDate;
        }
        return endDate;
    }

    /**
     * 获取BIM项目所需的截止时间
     *
     * @return
     */
    public static Date getEndDateOfBIM(String dateStr, String formatStr) {
        Date currentTime = parse(dateStr, formatStr);
        return getEndDateOfBIM(currentTime);
    }

    /**
     * 获取BIM项目所需的截止时间
     *
     * @return
     */
    public static String getEndDateStrOfBIM(Date currentTime, String formatStr) {
        Date endDate = getEndDateOfBIM(currentTime);
        return format(endDate, formatStr);
    }

    /**
     * 获取BIM项目所需的截止时间
     *
     * @return
     */
    public static String getEndDateStrOfBIM(String dateStr, String formatStr) {
        Date endDate = getEndDateOfBIM(dateStr, formatStr);
        return format(endDate, formatStr);

    }

    /**
     * 获取给定日期后intevalDay天的日期
     *
     * @param refenceDate 给定日期（格式为：yyyy-MM-dd）
     * @param intevalDays 间隔天数
     * @param format      日期格式
     * @return 计算后的日期
     */
    public static String getAfterDate(String refenceDate, int intevalDays, String format) {
        try {
            return getAfterDate(parse(refenceDate, format), intevalDays, format);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 获取给定日期后intevalDay天的日期
     *
     * @param refenceDate Date 给定日期
     * @param intevalDays int 间隔天数
     * @param format      日期格式
     * @return String 计算后的日期
     */
    public static String getAfterDate(Date refenceDate, int intevalDays, String format) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + intevalDays);
            return format(calendar.getTime(), format);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 获取给定日期前intevalDay天的日期
     *
     * @param refenceDate 给定日期（格式为：yyyy-MM-dd）
     * @param intevalDays 间隔天数
     * @param format      日期格式
     * @return 计算后的日期
     */
    public static String getBeforeDate(String refenceDate, int intevalDays, String format) {
        try {
            return getBeforeDate(parse(refenceDate, format), intevalDays, format);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 获取给定日期前intevalDay天的日期
     *
     * @param refenceDate Date 给定日期
     * @param intevalDays int 间隔天数
     * @param format      日期格式
     * @return String 计算后的日期
     */
    public static String getBeforeDate(Date refenceDate, int intevalDays, String format) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - intevalDays);
            return format(calendar.getTime(), format);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 获取给定日期前intevalYears年的日期
     *
     * @param refenceDate  计算日期
     * @param intevalYears 间隔年数
     * @param format       日期格式
     * @return 计算后的日期
     */
    public static String getBeforeYearDate(String refenceDate, int intevalYears, String format) {
        try {
            return getBeforeYearDate(parse(refenceDate, format), intevalYears, format);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取给定日期前intevalYears年的日期
     *
     * @param refenceDate  计算日期
     * @param intevalYears 间隔年数
     * @param format       日期格式
     * @return 计算后的日期
     */
    public static String getBeforeYearDate(Date refenceDate, int intevalYears, String format) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - intevalYears);
            return format(calendar.getTime(), format);

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取指定日期年份
     *
     * @param date 计算日期
     * @return 年份
     */
    public static String getYear(String date) {
        try {
            return getYear(parse(date, YYYY_MM_DD));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取指定日期年份
     *
     * @param date 计算日期
     * @return 年份
     */
    public static String getYear(Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return format(calendar.getTime(), YYYY);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取指定日期 -n年份
     *
     * @param date 计算日期
     * @param n    年份偏移量
     * @return
     */
    public static String getYear(Date date, int n) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + n);
            return format(calendar.getTime(), YYYY);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取指定日期 -n年份
     *
     * @param date 计算日期
     * @param n    年份偏移量
     * @return
     */
    public static String getYear(String date, int n) {
        try {
            return getYear(parse(date, YYYY_MM_DD), n);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取指定日期 -n小时分钟
     *
     * @param date
     * @return
     */
    public static String getHourAndMinute(Date date) {
        if (date == null) {
            return "";
        }
        String $date = format(date);
        if ($date.length() < 10) {
            return "";
        }
        //2017-08-28 12:12:12
        if ($date.length() >= 11 && $date.length() < 16) {
            String hourAndMinute = $date.substring(11);
            if (hourAndMinute.length() == 1) {
                return "0" + hourAndMinute + ":00";
            } else if (hourAndMinute.length() == 2) {
                return hourAndMinute + ":00";
            } else if (hourAndMinute.length() == 3) {
                return hourAndMinute + "0";
            }
            return hourAndMinute;
        }
        return $date.substring(11, 16);
    }

    /**
     *  获取指定日期 +-n月份日期，格式YYYY-MM
     * @param date 指定日期 格式YYYY-MM
     * @param n 月份偏移量
     * @return
     */
    public static String getYearMonth(String date,int n){
        return getYearMonth(parse(date,YYYY_MM),n);
    }

    /**
     *  获取指定日期 +-n月份日期，格式YYYY-MM
     * @param date 指定日期
     * @param n 月份偏移量
     * @return
     */
    public static String getYearMonth(Date date,int n){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + n);

            return format(calendar.getTime(),YYYY_MM);
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 获得两个时间的差值（注：hh:mm:ss）
     *
     * @Author zhang.l1@haihangyun.com
     * @param date1
     * @param date2
     * @return
     */
    public static String getTwoDateDifferTime(Date date1, Date date2) {
        if(date1 == null || date2 == null) {
            return "00:00:00";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        long currentTimeInMillis1 = calendar.getTimeInMillis();
        calendar.setTime(date2);
        long currentTimeInMillis2 = calendar.getTimeInMillis();
        long currentTimeInMillis = currentTimeInMillis1 - currentTimeInMillis2;

        long hour = currentTimeInMillis / (1000 * 60 * 60);

        StringBuilder $differTime = new StringBuilder();
        if (hour < 10) {
            $differTime.append("0");
        }
        $differTime.append(hour).append(":");
        currentTimeInMillis = currentTimeInMillis - (hour * 1000 * 60 * 60);
        long minute = currentTimeInMillis / (1000 * 60) ;
        if (minute < 10) {
            $differTime.append("0");
        }
        $differTime.append(minute).append(":");

        currentTimeInMillis = currentTimeInMillis - (minute * 1000 * 60);
        long second = currentTimeInMillis / 1000 ;
        if (second < 10) {
            $differTime.append("0");
        }
        $differTime.append(second);
        return $differTime.toString();
    }

    /**
     * 获取指定日期偏移n小时时间
     * @param date
     * @param n
     * @return
     */
    public static String getBeforeOrAfterHour(Date date,int n){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + n);

            return format(calendar.getTime(),SDF_YMDHMS);
        }catch (Exception e){
            return "";
        }
    }
    /**
     * 获取指定日期偏移n小时时间
     * @param date
     * @param n
     * @return
     */
    public static String getBeforeOrAfterHour(String date,int n){
        return getBeforeOrAfterHour(parse(date,SDF_YMDHMS),n);
    }

    /**
     * 获取指定时间偏移n秒的时间
     * @param date
     * @param n
     * @return
     */
    public static String getBeforeOfAfterSecond(Date date,int n){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + n);

            return format(calendar.getTime(),SDF_YMDHMS);
        }catch (Exception e){
            return "";
        }
    }
    /**
     * 获取指定时间偏移n秒的时间
     * @param date
     * @param n
     * @return
     */
    public static String getBeforeOfAfterSecond(String date,int n){
        return getBeforeOfAfterSecond(parse(date,SDF_YMDHMS),n);
    }

    /**
     * 獲取指定日期  定製時分秒
     * @param date
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     */
    public static String getFullDateTime(Date date,int hours, int minutes, int seconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, 0);

        return format(calendar.getTime(),SDF_YMDHMS);
    }
    /**
     * 獲取指定日期  定製時分秒
     * @param date
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     */
    public static String getFullDateTime(String date,int hours, int minutes, int seconds){
        return getFullDateTime(parse(date,SDF_YMDHMS),hours,minutes,seconds);
    }

    /**
     * 獲取BIM結束時間  查詢日期的05:00:00
     * @param date
     * @return
     */
    public static String getEndDateForBIM(String date){
        return getFullDateTime(date,5,0,0);
    }

    /**
     * 獲取BIM開始時間  查詢日期-1的05:00:00
     * @param date
     * @return
     */
    public static String getStartDateForBIM(String date){
        String yesDate = getBeforeDate(date,1,SDF_YMDHMS);
        return getFullDateTime(yesDate,5,0,0);
    }
}
