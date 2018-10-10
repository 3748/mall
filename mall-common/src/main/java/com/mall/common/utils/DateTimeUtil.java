package com.mall.common.utils;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 *
 * @author gp6
 * @date 2018-07-09
 */
public class DateTimeUtil {

    private static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 获取指定格式(YYYYMMDDHHMMSS)的当前时间 eg:20181231123456
     */
    public static final Long CURRENTTIME = getCurrentTime();

    /**
     * 获取当前年
     */
    public static final String CURRENTYEAR = getCurrentYear();

    /**
     * 获取当前月
     */
    public static final String CURRENTMOUTH = getCurrentMouth();

    /**
     * 获取当前日
     */
    public static final String CURRENTDATE = getCurrentDate();

    private static Long getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return Long.valueOf(formatter.format(new Date()));
    }

    private static String getCurrentYear() {
        Date nowDate = new Date();
        return new DateTime(nowDate).toString("yyyy");
    }

    private static String getCurrentMouth() {
        Date nowDate = new Date();
        return new DateTime(nowDate).toString("MM");
    }

    private static String getCurrentDate() {
        Date nowDate = new Date();
        return new DateTime(nowDate).toString("dd");
    }
}
