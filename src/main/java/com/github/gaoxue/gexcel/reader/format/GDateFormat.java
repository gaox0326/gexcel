package com.github.gaoxue.gexcel.reader.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.gaoxue.common.StringUtil;

/**
 * Date format class.
 * <p>Format a date into date/time string.
 * <p>Parse a string to a date.
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

    public GDateFormat(String pattern) {
        if (StringUtil.isNullOrEmpty(pattern)) {
            format = DEFAULT_DATE_FORMAT;
        } else {
            format = new SimpleDateFormat(pattern);
        }
    }

    /**
     * Parse a string to a date
     * @param str string value
     * @return parsed date
     * @throws ParseException
     */
    public Date parse(String str) throws ParseException {
        return format.parse(str);
    }

    /**
     * Format a date into date/time string
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

    /**
     * Return date format pattern
     * @return date format pattern
     */
    public String getPattern() {
        return ((SimpleDateFormat) format).toPattern();
    }

}
