package com.github.gaoxue.gexcel.reader.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.gaoxue.common.StringUtil;
import com.github.gaoxue.gexcel.exception.ExcelParseException;

/**
 * Date format class.
 * <p>Format Date into date/time String.
 * <p>Parse String to Date.
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
     * Parse String to Date
     * @param str String value
     * @return parsed Date
     * @throws ExcelParseException if parse failed with {@link DateFormat#parse(String)}
     */
    public Date parse(String str) throws ExcelParseException {
        try {
            return format.parse(str);
        } catch (ParseException ex) {
            throw new ExcelParseException("Date parse error with String value " + str + ", reason: " + ex.getMessage(), ex);
        }
    }

    /**
     * Format Date into date/time String
     * @param date Date value
     * @return formated String
     */
    public String format(Date date) {
        String result = "";
        if (date != null) {
            result = format.format(date);
        }
        return result;
    }

    /**
     * Return Date format pattern
     * @return Date format pattern
     */
    public String getPattern() {
        return ((SimpleDateFormat) format).toPattern();
    }

}
