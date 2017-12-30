package com.github.gaoxue.gexcel.config;

import com.github.gaoxue.gexcel.reader.format.GBooleanFormat;
import com.github.gaoxue.gexcel.reader.format.GDateFormat;
import com.github.gaoxue.gexcel.reader.format.GDoubleFormat;

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

    /** Date format */
    private GDateFormat dateFormat = new GDateFormat();

    /** Boolean format */
    private GBooleanFormat booleanFormat = new GBooleanFormat();

    /** Double format */
    private GDoubleFormat doubleFormat = new GDoubleFormat();

    /**
     * Get Date format
     * @return date format
     */
    public GDateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Get Boolean format
     * @return boolean format
     */
    public GBooleanFormat getBooleanFormat() {
        return booleanFormat;
    }

    /**
     * Get Double format
     * @return
     */
    public GDoubleFormat getDoubleFormat() {
        return doubleFormat;
    }

    /**
     * Set Date format pattern
     * <p>When Field type is Date, excel string value will be parsed to Date with this pattern.
     * @param pattern Date format pattern
     */
    public void setDateFormatPattern(String pattern) {
        dateFormat = new GDateFormat(pattern);
    }

    /**
     * Set {@code true} String value for format
     * <p>When Field type is String, excel boolean value {@code true} will be formated to String with this String value.
     * @param trueFormat {@code true} String value for format
     */
    public void setTrueFormat(String trueFormat) {
        booleanFormat.setTrueFormat(trueFormat);
    }

    /**
     * Set {@code false} String value for format
     * <p>When Field type is String, excel boolean value {@code false} will be formated to String with this String value.
     * @param falseFormat {@code false} String value for format
     */
    public void setFalseFormat(String falseFormat) {
        booleanFormat.setFalseFormat(falseFormat);
    }
}
