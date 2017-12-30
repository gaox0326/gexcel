package com.github.gaoxue.gexcel.reader.format;

import com.github.gaoxue.gexcel.exception.ExcelParseException;

/**
 * Double format class.
 * <p>Format Double/double into String.
 * <p>Parse String to Double.
 * 
 * @author gaoxue
 */
public class GDoubleFormat {

    /**
     * Parse String to Double
     * @param str String value
     * @return
     */
    public Double parse(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            throw new ExcelParseException("Double parse error with String value " + str + ", reason: " + ex.getMessage(), ex);
        }
    }

    /**
     * Format Double/double into String
     * @param gdouble Double/double value
     * @return formatted String
     */
    public String format(Double gdouble) {
        if (gdouble == null) {
            return "null";
        }
        return gdouble.toString();
    }
}
