package com.github.gaoxue.gexcel.config;

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

    /**
     * Get date format
     * @return date format
     */
    public GDateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Set date format pattern
     * <p>When Field type is Date, excel string value will be parsed to date with this pattern.
     * @param pattern date format pattern
     */
    public void setDateFormatPattern(String pattern) {
        dateFormat = new GDateFormat(pattern);
    }

}
