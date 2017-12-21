package com.github.gaoxue.gexcel.reader.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date format class.
 * <p>Formats a date into date/time string.
 * <p>Parses a string to a date.
 * <p>Ignore multiple threads.
 * 
 * @author gaoxue
 */
public class GDateFormat {

    /** default date format */
    private static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private DateFormat format;

    public GDateFormat() {
        format = DEFAULT_DATE_FORMAT;
    }

    public GDateFormat(DateFormat format) {
        this.format = format;
    }

    /**
     * Parses a string to a date
     * @param str string value
     * @return parsed date
     * @throws ParseException
     */
    public Date parse(String str) throws ParseException {
        return format.parse(str);
    }

    /**
     * Formats a date into date/time string
     * @param date date value
     * @return formated string
     */
    public String format(Date date) {
        String result = "";
        if (date != null) {
            result = format.format(date);
        }
        return result;
    }
}
