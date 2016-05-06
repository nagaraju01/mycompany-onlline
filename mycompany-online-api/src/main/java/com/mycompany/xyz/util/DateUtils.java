/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.apache.commons.lang.StringUtils.isBlank;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * DateUtils class - that is used to prepare data for the new lead screen.
 *
 */
public class DateUtils {
	
    public final static String DATE_FORMAT = "dd/MM/yyyy";

    public final static String DATE_FORMAT_PATTERN = "\\d{2}/\\d{2}/20\\d{2}";

    public final static String TIME_FORMAT = "HH:mm";

    public static final String TIME_FORMAT_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public final static String DATE_FORMAT_SECONDS = "dd/MM/yyyy HH:mm:ss";

    public final static String DATE_FORMAT_MINUTES = "dd/MM/yyyy HH:mm";

    public final static String DATE_FORMAT_DB = "yyyy-MM-dd HH:mm:ss";
    
    public final static DateTimeFormatter ISO_DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z");

    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT);

    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT + " " + TIME_FORMAT);

    public final static DateTimeFormatter DATE_FORMATTER_DB = DateTimeFormat.forPattern(DATE_FORMAT_DB);


    /**
     * gets GregorianCalendar with date
     *
     * @param sDate
     * @return GregorianCalendar
     */
    public GregorianCalendar getGCDate(String sDate) {
        Date dt = getDate(sDate);
        if (dt == null) {
            return null;
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dt);
        return gc;
    }

    /**
     * Sets time to given {@link DateTime} and returns object with given time
     *
     * @param date
     * @param time
     * @return Joda's {@link DateTime} object or null if given time has not
     * correct format or is null.
     */
    public static DateTime setTimeToDate(DateTime date, String time) {
        if (isBlank(time) || !time.matches(TIME_FORMAT_PATTERN)) {
            return null;
        }
        final String strDate = date.toString(DATE_FORMAT);
        return DATE_TIME_FORMATTER.parseDateTime(strDate + " " + time);
    }

    /**
     * gets GregorianCalendar with date & time.
     *
     * @param sDateTime
     * @return GregorianCalendar
     */
    public static GregorianCalendar getGCDateTime(final String sDateTime) {

        if (isBlank(sDateTime)) {
            return null;
        }

        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DB);
            Date dt = sdf.parse(sDateTime);
            if (dt == null) {
                return null;
            }
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dt);
            return gc;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * gets GregorianCalendar date time
     *
     * @param date
     * @param pattern
     * @return GregorianCalendar
     * @throws ParseException
     */
    public static GregorianCalendar getGCDateTime(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d = sdf.parse(date);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal;
    }

    /**
     * Method that tests and converts string dates to Date objects.
     *
     * @param sDate input Date string
     * @return Returns null if input Date string is empty or invalid
     */
    public static Date getDate(String sDate) {

        if (isBlank(sDate)) {
            return null;
        }

        try {
            String Date1 = validateDate(sDate);
            if (Date1 == null) {
                return null;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                return sdf.parse(Date1);
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * converts date string to db format string (yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return String
     * @throws ParseException
     */
    public static String convertDateStringToDB(String date) throws ParseException {
        GregorianCalendar origDate = new GregorianCalendar();
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
        origDate.setTime(sdf1.parse(date));
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DB);
        String dbFormattedDate = sdf.format(origDate.getTime());
        return dbFormattedDate;
    }

    /**
     * validates the date entered in the web
     *
     * @param inDate
     * @return String
     */
    private static String validateDate(String inDate) {
        int j;
        String tempDate = inDate;

        try {

            j = inDate.indexOf(".");
            if (j != -1) {
                tempDate = inDate.substring(0, j) + "/" + inDate.substring(j + 1);
            }
            j = inDate.indexOf(".");
            if (j != -1) {
                tempDate = inDate.substring(0, j) + "/" + inDate.substring(j + 1);
            }
            j = tempDate.indexOf("/");
            if (j == -1) {
                return null;
            }
            if (j == 1) {
                tempDate = "0" + tempDate;
            }
            j = tempDate.indexOf("/", 3);
            if (j == -1) {
                return null;
            }
            if (j == 4) {
                tempDate = tempDate.substring(0, 3) + "0" + tempDate.substring(j - 1);
            }
            if (tempDate.length() == 8) {
                tempDate = tempDate.substring(0, 6) + "20" + tempDate.substring(6);
            }

        } catch (Exception ex) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        java.util.Date dateConv;

        try {
            df.setLenient(false);
            dateConv = df.parse(tempDate);
            if (dateConv == null) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
        return tempDate;
    }

    /**
     * is date
     *
     * @param input
     * @return boolean
     */
    public static boolean isDate(String input) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date d = sdf.parse(input);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * to Gregorian Calender
     *
     * @param date
     * @return GregorianCalendar
     */
    public static GregorianCalendar toGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());
        return gc;
    }
    
    /**
     * Converts a given date from GregorianCalendar to String in 'dd/MM/yyyy HH:mm' pattern.
     *
     * @param date
     * @return String
     */
    public static String getDateTimeStringFromGC (GregorianCalendar date) {
    	return new SimpleDateFormat(DATE_FORMAT_MINUTES).format(date.getTime());
    }
}