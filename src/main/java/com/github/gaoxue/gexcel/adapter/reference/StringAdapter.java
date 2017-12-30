package com.github.gaoxue.gexcel.adapter.reference;

import java.util.Date;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.config.EnvironmentConfig;
import com.github.gaoxue.gexcel.reader.Reader;
import com.github.gaoxue.gexcel.reader.format.GDateFormat;

/**
 * String adapter.
 * 
 * @author gaoxue
 */
public class StringAdapter implements TypeAdapter<String> {

    public String read(Reader reader) {
        Object obj = reader.readObject();
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            GDateFormat format = EnvironmentConfig.getInstance().getDateFormat();
            return format.format((Date) obj);
        }
        Class<? extends Object> clazz = obj.getClass();
        if (clazz.isAssignableFrom(boolean.class)) {
            return EnvironmentConfig.getInstance().getBooleanFormat().format((Boolean) obj);
        }
        if (clazz.isAssignableFrom(double.class)) {
            return EnvironmentConfig.getInstance().getDoubleFormat().format((double) obj);
        }
        return obj.toString();
    }

}
