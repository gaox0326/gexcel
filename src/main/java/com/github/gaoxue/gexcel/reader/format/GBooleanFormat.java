package com.github.gaoxue.gexcel.reader.format;

/**
 * Boolean format class.
 * <p>Format a Boolean/boolean into string.
 * <p>Parse a string to a Boolean.
 * 
 * @author gaoxue
 */
public class GBooleanFormat {

    /** {@code true} string value for format */
    private String trueFormat = "true";

    /** {@code false} string value for format */
    private String falseFormat = "false";

    /**
     * Parse a string to a Boolean, when {@link trueFormat} return {@code true}, otherwise {@code false}
     * @param str string value
     * @return parsed Boolean
     */
    public Boolean parse(String str) {
        return trueFormat.equals(str);
    }

    /**
     * Format a Boolean/boolean into string
     * @param bool Boolean/boolean value
     * @return formatted string
     */
    public String format(Boolean bool) {
        if (bool == null) {
            return "null";
        }
        return bool ? trueFormat : falseFormat;
    }

    /**
     * Get {@code true} string value for format
     * @return {@code true} string value for format
     */
    public String getTrueFormat() {
        return trueFormat;
    }

    /**
     * Set {@code true} string value for format
     * <p>When Field type is String, excel boolean value {@code true} will be formated to String with this string value.
     * @param trueFormat {@code true} string value for format
     */
    public void setTrueFormat(String trueFormat) {
        this.trueFormat = trueFormat;
    }

    /**
     * Get {@code false} string value for format
     * @return {@code false} string value for format
     */
    public String getFalseFormat() {
        return falseFormat;
    }

    /**
     * Set {@code false} string value for format
     * <p>When Field type is String, excel boolean value {@code false} will be formated to String with this string value.
     * @param falseFormat {@code false} string value for format
     */
    public void setFalseFormat(String falseFormat) {
        this.falseFormat = falseFormat;
    }
}
