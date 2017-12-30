package com.github.gaoxue.gexcel.config;

import com.github.gaoxue.gexcel.reader.format.GBooleanFormat;
import com.github.gaoxue.gexcel.reader.format.GDateFormat;

/**
 * Environment configurations.
 * 
 * @author gaoxue
 */
public class EnvironmentConfig {

    private EnvironmentConfig() { }

    private static class Singleton {
        private static final EnvironmentConfig INSTANCE = new EnvironmentConfig();
    }

    public static final EnvironmentConfig getInstance() {
        return Singleton.INSTANCE;
    }

    /** date format */
    private GDateFormat dateFormat = new GDateFormat();

    /** boolean format */
    private GBooleanFormat booleanFormat = new GBooleanFormat();

    /**
     * Get date format
     * @return date format
     */
    public GDateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Get boolean format
     * @return boolean format
     */
    public GBooleanFormat getBooleanFormat() {
        return booleanFormat;
    }

    /**
     * Set date format pattern
     * <p>When Field type is Date, excel string value will be parsed to date with this pattern.
     * @param pattern date format pattern
     */
    public void setDateFormatPattern(String pattern) {
        dateFormat = new GDateFormat(pattern);
    }

    /**
     * Set {@code true} string value for format
     * <p>When Field type is String, excel boolean value {@code true} will be formated to String with this string value.
     * @param trueFormat
     */
    public void setTrueFormat(String trueFormat) {
        booleanFormat.setTrueFormat(trueFormat);
    }

    /**
     * Set {@code false} string value for format
     * <p>When Field type is String, excel boolean value {@code false} will be formated to String with this string value.
     * @param falseFormat {@code false} string value for format
     */
    public void setFalseFormat(String falseFormat) {
        booleanFormat.setFalseFormat(falseFormat);
    }
}
