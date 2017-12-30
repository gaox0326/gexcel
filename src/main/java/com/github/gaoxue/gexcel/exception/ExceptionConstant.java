package com.github.gaoxue.gexcel.exception;

/**
 * Exception constants
 * @author gaoxue
 */
public final class ExceptionConstant {

    public static final String UNEXPECTED_TYPE = "Expected a %s but was %s: %s.";

    public static final String OUT_OF_BOUNDS = "Value out of bounds, expected a %s but was %s.";

    public static ExcelParseException getUnexpectedTypeEx(String expected, String actual, String value) {
        return new ExcelParseException(String.format(UNEXPECTED_TYPE, expected, actual, value));
    }

    public static ExcelParseException getOutOfBoundsEx(String expected, String value) {
        return new ExcelParseException(String.format(OUT_OF_BOUNDS, expected, value));
    }

    public ExceptionConstant() {
        // final utility class, avoid instantiate
    }
}
