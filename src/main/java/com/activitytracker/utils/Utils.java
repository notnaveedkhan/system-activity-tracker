package com.activitytracker.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Utils {

    public static int getRandomDelay(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static void sleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
        }
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date(System.currentTimeMillis()));
    }

    public static String getFormattedTimeStamp(String format) {
        return new SimpleDateFormat(format).format(new Date(System.currentTimeMillis()));
    }

    public static String getFormattedDate(String format) {
        return new SimpleDateFormat(format).format(new Date(System.currentTimeMillis()));
    }

}
