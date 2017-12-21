package com.github.gaoxue.gexcel.reader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gaoxue
 */
public class Format {

    // TODO customer format

    /** default date format, ignore multiple threads */
    private static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String format(Date date) {
        String result = "";
        if (date != null) {
            result = DEFAULT_DATE_FORMAT.format(date);
        }
        return result;
    }

    // format to String
    public static String format(double value) {
        return Double.toString(value);
    }

    public static String format(boolean value) {
        return Boolean.toString(value);
    }

    public static double format2Double(String value) {
        return Double.parseDouble(value);
    }

    public static double format2Double(Date date) {
        return date.getTime();
    }

    public static Date format2Date(double value) {
        return new Date((long) value);
    }

    public static Boolean format2Boolean(String value) {
        return Boolean.parseBoolean(value);
    }

}
