package com.github.gaoxue.gexcel.reader.format;

/**
 * Boolean format class.
 * <p>Format Boolean/boolean into String.
 * <p>Parse String to Boolean.
 * 
 * @author gaoxue
 */
public class GBooleanFormat {

    /** {@code true} String value for format */
    private String trueFormat = "true";

    /** {@code false} String value for format */
    private String falseFormat = "false";

    /**
     * Parse String to Boolean, when {@link trueFormat} return {@code true}, otherwise {@code false}
     * @param str String value
     * @return parsed Boolean
     */
    public Boolean parse(String str) {
        return trueFormat.equals(str);
    }

    /**
     * Format Boolean/boolean into String
     * @param bool Boolean/boolean value
     * @return formatted String
     */
    public String format(Boolean bool) {
        if (bool == null) {
            return "null";
        }
        return bool ? trueFormat : falseFormat;
    }

    /**
     * Format Boolean/boolean into Integer
     * @param bool Boolean/boolean value
     * @return formatted Integer
     */
    public Integer format2Int(Boolean bool) {
        return bool != null && bool ? 1 : 0;
    }

    /**
     * Get {@code true} String value for format
     * @return {@code true} String value for format
     */
    public String getTrueFormat() {
        return trueFormat;
    }

    /**
     * Set {@code true} String value for format
     * <p>When Field type is String, excel boolean value {@code true} will be formated to String with this String value.
     * @param trueFormat {@code true} String value for format
     */
    public void setTrueFormat(String trueFormat) {
        this.trueFormat = trueFormat;
    }

    /**
     * Get {@code false} String value for format
     * @return {@code false} String value for format
     */
    public String getFalseFormat() {
        return falseFormat;
    }

    /**
     * Set {@code false} String value for format
     * <p>When Field type is String, excel boolean value {@code false} will be formated to String with this String value.
     * @param falseFormat {@code false} String value for format
     */
    public void setFalseFormat(String falseFormat) {
        this.falseFormat = falseFormat;
    }

}
