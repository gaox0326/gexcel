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
        if (obj instanceof Boolean) {
            return EnvironmentConfig.getInstance().getBooleanFormat().format((Boolean) obj);
        }
        if (obj instanceof Double) {
            return EnvironmentConfig.getInstance().getDoubleFormat().format((Double) obj);
        }
        return obj.toString();
    }

}
