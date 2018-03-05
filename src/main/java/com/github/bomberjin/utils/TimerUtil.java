package com.github.bomberjin.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerUtil {
    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTimeForString() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;
    }

    /**
     * 根据格式获取当前时间
     * @param format
     * @return
     */
    public static String getNowTimeForString(String format) {
        Date date = new Date();
        DateFormat formatDate = new SimpleDateFormat(format);
        String time = formatDate.format(date);
        return time;
    }
}
