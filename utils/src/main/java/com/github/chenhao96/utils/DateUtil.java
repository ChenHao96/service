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
        Calendar calendar = getZeroTimeDate(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - date);
        return calendar.getTime();
    }

    private static Calendar getZeroTimeDate(Date time) {
        long dateTime = time.getTime();
        dateTime -= dateTime % 86400000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTime);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
    }

    public static Date timeOnlyDate(Date time) {
        return getZeroTimeDate(time).getTime();
    }

    public static Date timeNextSecondNewDate(Date time) {
        Calendar calendar = getZeroTimeDate(time);
        calendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND) - 1);
        return calendar.getTime();
    }

    public static int calculateDay(Date beginTime, Date endTime) {
        long time1 = beginTime.getTime();
        long time2 = endTime.getTime();
        long between_days = (time2 - time1) / 86400000;
        return Integer.parseInt(String.valueOf(between_days));
    }
}
