package com.github.gaoxue.gexcel.exception;

/**
 * Exception constants and utility class
 * @author gaoxue
 */
public final class ExceptionConstant {

    /** unexpected type exception message format */
    public static final String UNEXPECTED_TYPE = "Expected a %s but was %s: %s.";

    /** out of bounds exception message format */
    public static final String OUT_OF_BOUNDS = "Value out of bounds, expected a %s but was %s.";

    /**
     * Get unexpected value type exception
     * @param expected expected type
     * @param actual actual type
     * @param value value
     * @return unexpected value type exception with message
     */
    public static ExcelParseException getUnexpectedTypeEx(String expected, String actual, String value) {
        return new ExcelParseException(String.format(UNEXPECTED_TYPE, expected, actual, value));
    }

    /**
     * Get out of bounds exception
     * @param expected expected type
     * @param value value
     * @return out of bounds exception with message
     */
    public static ExcelParseException getOutOfBoundsEx(String expected, String value) {
        return new ExcelParseException(String.format(OUT_OF_BOUNDS, expected, value));
    }

    private ExceptionConstant() {
        // final utility class, avoid instantiate
    }
}
