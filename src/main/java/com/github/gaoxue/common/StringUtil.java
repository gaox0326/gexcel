package com.github.gaoxue.common;

/**
 * String utility
 * 
 * @author gaoxue
 */
public final class StringUtil {

    /**
     * Returns {@code true} if {@code value} is {@code null} or {@linkplain String#isEmpty() empty}.
     * @param value the string value
     * @return {@code true} if {@code value} is {@code null} or {@linkplain String#isEmpty() empty}, otherwise {@code false}
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private StringUtil() {
        // final utility class, avoid instantiate
    }
}
