package com.github.gaoxue.gexcel.reader.format;

import java.text.DecimalFormat;
import java.text.ParseException;

import com.github.gaoxue.common.StringUtil;
import com.github.gaoxue.gexcel.exception.ExcelParseException;

/**
 * Double format class.
 * <p>Format Double/double into String.
 * <p>Parse String to Double.
 * 
 * @author gaoxue
 */
public class GDoubleFormat {

    /** default double format */
    private static DecimalFormat DEFAULT_DATE_FORMAT = new DecimalFormat("#.##");

    private DecimalFormat format;

    public GDoubleFormat() {
        format = DEFAULT_DATE_FORMAT;
    }

    public GDoubleFormat(String pattern) {
        if (StringUtil.isNullOrEmpty(pattern)) {
            format = DEFAULT_DATE_FORMAT;
        } else {
            format = new DecimalFormat(pattern);
        }
    }

    /**
     * Parse String to Double
     * <p>When {@code str} is {@code null} return {@code null}.
     * @param str String value
     * @return parsed Double
     * @throws ExcelParseException if parse failed with {@link Double#parseDouble(String)}
     */
    public Double parse(String str) throws ExcelParseException {
        if (str == null) {
            return null;
        }
        try {
            return format.parse(str).doubleValue();
        } catch (ParseException ex) {
            throw new ExcelParseException("Double parse error with String value " + str + ", reason: " + ex.getMessage(), ex);
        }
    }

    /**
     * Format Double/double into String
     * <p>When {@code gdouble} is {@code null} return "null".
     * @param gdouble Double/double value
     * @return formatted String
     */
    public String format(Double gdouble) {
        if (gdouble == null) {
            return "null";
        }
        return format.format(gdouble);
    }

    /**
     * Return Double format pattern
     * @return Double format pattern
     */
    public String getPattern() {
        return format.toPattern();
    }
}
