package com.github.gaoxue.gexcel.adapter.reference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.gaoxue.common.StringUtil;
import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reader.Reader;
import com.github.gaoxue.gexcel.reader.format.GDateFormat;

/**
 * {@code Date} adapter.
 * <p>When handle string value, parse with {@link #format} which can be provided by constructor
 * <p>Default format pattern "yyyy-MM-dd"
 * @author gaoxue
 */
public class DateAdapter implements TypeAdapter<Date> {

    /** date format */
    private GDateFormat format;

    public DateAdapter() {
        format = new GDateFormat();
    }

    public DateAdapter(String format) {
        if (StringUtil.isNullOrEmpty(format)) {
            this.format = new GDateFormat();
        } else {
            this.format = new GDateFormat(new SimpleDateFormat(format));
        }
    }

    public Date read(Reader reader) {
        Object obj = reader.readObject();
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        Class<? extends Object> clazz = obj.getClass();
        if (clazz.isAssignableFrom(boolean.class)) {
            throw new ExcelParseException("Expected a Date but was boolean: " + obj + ".");
        }
        if (clazz.isAssignableFrom(double.class)) {
            throw new ExcelParseException("Expected a Date but was double: " + obj + ".");
        }
        try {
            return format.parse(obj.toString());
        } catch (ParseException ex) {
            throw new ExcelParseException("Parse " + obj.toString() + " to a Date failed: " + ex.getMessage() + ".");
        }
    }

}
