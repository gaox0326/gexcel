package com.github.gaoxue.gexcel.adapter.reference;

import java.util.Date;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.config.EnvironmentConfig;
import com.github.gaoxue.gexcel.exception.ExceptionConstant;
import com.github.gaoxue.gexcel.reader.Reader;

/**
 * {@code Date} adapter.
 * <p>Excel string value will be parsed with format provided by {@link EnvironmentConfig}
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
        if (obj instanceof String) {
            return EnvironmentConfig.getInstance().getDateFormat().parse(obj.toString());
        }
        throw ExceptionConstant.getUnexpectedTypeEx("Date", obj.getClass().getName(), obj.toString());
    }

}
