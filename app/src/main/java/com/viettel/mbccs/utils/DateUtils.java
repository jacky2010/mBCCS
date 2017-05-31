package com.viettel.mbccs.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String CALENDAR_DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIMEZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String REVIEW_TIME_FORMAT = "yyyy年MM月dd日";
    public static final String BIRTHDAY_TIME_FORMAT = "yyyy/MM/dd";
    public static final String WORKING_TIME_FORMAT = "%1$s~%2$s";
    public static final String DATE_PATTERN = "^[0-9]{4}-[0-9]{2}-[0-9]{2}";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_PICKER_FORMAT = "MMM dd, yyyy";
    public static final long INVALID_TIME = -1;
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_FORMAT1 = "dd/MM/yyyy";

    public static Date stringToDate(String time, String format, Locale locale) {
        if (TextUtils.isEmpty(time) || TextUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, locale);
        Date date;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    public static long timeToLong(String time, String format, Locale locale) {
        if (TextUtils.isEmpty(time) || TextUtils.isEmpty(format)) {
            return INVALID_TIME;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, locale);
        Date date;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            return INVALID_TIME;
        }
        return date.getTime();
    }

    public static String timestampToString(long timestamp, String format, @Nullable Locale locale) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(format, locale == null ? Locale.getDefault() : locale);
        Date date = new Date(timestamp);
        return simpleDateFormat.format(date);
    }

    public static String changeDateFormat(String oldDate, String oldFormat, String newFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(oldFormat, Locale.getDefault());
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            calendar.setTimeInMillis(simpleDateFormat.parse(oldDate).getTime());
            simpleDateFormat.applyPattern(newFormat);
            return simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static long timezoneToLong(String timezone) {
        return timeToLong(timezone, TIMEZONE_FORMAT, Locale.JAPAN);
    }

    public static boolean isExpired(String time) {
        return System.currentTimeMillis() > timezoneToLong(time);
    }

    public static String changeTimeFormat(@NonNull String oldTime, @NonNull String oldFormat,
            @NonNull String newFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(oldFormat, Locale.JAPAN);
        Date date;
        try {
            date = simpleDateFormat.parse(oldTime);
            simpleDateFormat.applyPattern(newFormat);
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getFormatedTime(int hourOfDay, int minute) {
        String format = "%1$02d:%2$02d";
        return String.format(format, hourOfDay, minute);
    }

    public static Date convertToDate(String source, String format) {
        if (source == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            Date date = formatter.parse(source);
            return date;
        } catch (ParseException e) {

        }
        return null;
    }

    public static String convertDateToString(long time, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time));
    }

    public static String convertToString(Date source, String format, @Nullable Locale locale) {
        if (source == null) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(format, locale == null ? Locale.getDefault() : locale);
        return df.format(source);
    }
}
