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
            return Double.parseDouble(str);
        } catch (NumberFormatException ex) {
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
        // TODO use DecimalFormat
        return gdouble.toString();
    }
}
