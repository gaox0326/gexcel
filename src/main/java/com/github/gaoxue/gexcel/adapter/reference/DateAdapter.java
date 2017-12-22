package com.github.gaoxue.gexcel.adapter.reference;

import java.text.ParseException;
import java.util.Date;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.config.EnvironmentConfig;
import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reader.Reader;
import com.github.gaoxue.gexcel.reader.format.GDateFormat;

/**
 * {@code Date} adapter.
 * <p>When handle string value, parse with {@link #format} which provided by {@link EnvironmentConfig}
 * <p>Default format pattern "yyyy-MM-dd"
 * @author gaoxue
 */
public class DateAdapter implements TypeAdapter<Date> {

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
        GDateFormat format = EnvironmentConfig.getInstance().getDateFormat();
        try {
            return format.parse(obj.toString());
        } catch (ParseException ex) {
            throw new ExcelParseException("Excepted a Date bu was String: " + obj + ", parse with pattern " + format.getPattern() + " failed: " + ex.getMessage() + ".");
        }
    }

}
