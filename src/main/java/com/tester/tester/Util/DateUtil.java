package com.tester.tester.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    private static final String timeFormat = "yyyy-MM-dd HH:mm:ss";

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getCurrentDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        return formatter.format(getCurrentDate());
    }

    public static String getCurrentDateFormatPointFormatDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(getCurrentDate());
    }

    public static long getPointDateFormatTimestamp(String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        Date date = formatter.parse(time);
        return millisecondFormatSeconds(date.getTime());
    }

    public static String getPointTimestampFormatDate(Long timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);

        return formatter.format(new Date(timestamp * 1000L));
    }

    public static long getCurrentTimestamp() {
        return millisecondFormatSeconds(getCurrentDate().getTime());
    }

    public static long getZeroTimestamp() {
        long current = getTimestamp();
        return millisecondFormatSeconds((current /(1000 * 3600 *24) * (1000*3600*24) - TimeZone.getDefault().getRawOffset()));
    }

    public static long getPointTimestamp(int hour) {
        return getZeroTimestamp() + hourFormatMinute(hour);
    }

    private static long hourFormatMinute(int hour) {
        return hour * 60 * 60;
    }

    public static long millisecondFormatSeconds(Long timestamp) {
        return timestamp / 1000;
    }

    public static void main(String[] args) {
        System.out.println(getPointTimestampFormatDate(1598508000L));
    }

    public static long getTimestamp() {
        return System.currentTimeMillis();
    }
}
