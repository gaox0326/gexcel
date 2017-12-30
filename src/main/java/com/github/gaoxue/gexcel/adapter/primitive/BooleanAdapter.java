package com.github.gaoxue.gexcel.adapter.primitive;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.config.EnvironmentConfig;
import com.github.gaoxue.gexcel.exception.ExceptionConstant;
import com.github.gaoxue.gexcel.reader.Reader;

/**
 * {@code Boolean} adapter, handle {@code Boolean} and {@code boolean} type.
 * 
 * @author gaoxue
 */
public final class BooleanAdapter implements TypeAdapter<Boolean> {

    public static final BooleanAdapter BOOLEAN = new BooleanAdapter();

    private BooleanAdapter() {
        // avoid instantiate, use final field BOOLEAN
    }

    @Override
    public Boolean read(Reader reader) {
        Object obj = reader.readObject();
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            return EnvironmentConfig.getInstance().getBooleanFormat().parse((String) obj);
        }
        throw ExceptionConstant.getUnexpectedTypeEx("Boolean", obj.getClass().getName(), obj.toString());
    }

}
