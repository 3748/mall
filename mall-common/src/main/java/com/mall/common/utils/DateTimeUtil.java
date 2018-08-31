package com.mall.common.utils;

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
     * 指定格式(YYYYMMDDHHMMSS)的当前时间 eg:20181231123456
     */
    public static final Long CURRENTTIME = DateTimeUtil.getCurrentTime();

    public static Long getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return Long.valueOf(formatter.format(new Date()));
    }


}
