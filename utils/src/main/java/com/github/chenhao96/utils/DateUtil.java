package com.github.chenhao96.utils;

import java.util.Calendar;
import java.util.Date;

final public class DateUtil {

    private DateUtil() {
    }

    public static Date firstTimeOnlyDateForCalendar(Date month) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeOnlyDate(month));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int lastWeekDaySize = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DATE) - lastWeekDaySize);

        return calendar.getTime();
    }

    public static Date newDistanceDay(Integer date) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date timeOnlyDate(Date time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTime();
    }

    public static Date timeNextSecondNewDate(Date time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.HOUR_OF_DAY, 23);

        return calendar.getTime();
    }

    public static int calculateDay(Date beginTime, Date endTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginTime);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endTime);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
