package com.github.gaoxue.gexcel.adapter.primitive;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.config.EnvironmentConfig;
import com.github.gaoxue.gexcel.exception.ExceptionConstant;
import com.github.gaoxue.gexcel.reader.Reader;

/**
 * Integral type adapters.
 * <p>
 * Handle {@code Byte} {@code byte}, {@code Short} {@code short},
 * {@code Integer} {@code int}, {@code Long} {@code long},
 * {@code Character} {@code char}, {@code Double} {@code double},
 * {@code Float} {@code float}.
 * 
 * @author gaoxue
 */
public class IntegralTypeAdapters {

    static EnvironmentConfig config = EnvironmentConfig.getInstance();

    public static final TypeAdapter<Byte> BYTE = new TypeAdapter<Byte>() {

        @Override
        public Byte read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Boolean) {
                return config.getBooleanFormat().format2Int((Boolean) obj).byteValue();
            }
            Double doubleValue;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            } else if (obj instanceof String) {
                doubleValue = config.getDoubleFormat().parse((String) obj);
            } else { // Date and other
                throw ExceptionConstant.getUnexpectedTypeEx("Byte", obj.getClass().getName(), obj.toString());
            }
            byte value = doubleValue.byteValue();
            if (doubleValue.doubleValue() != value) {
                // Make sure no precision was lost casting to 'byte'.
                throw ExceptionConstant.getOutOfBoundsEx("Byte", Double.toString(doubleValue));
            }
            return value;
        }
    };

    public static final TypeAdapter<Short> SHORT = new TypeAdapter<Short>() {

        @Override
        public Short read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Boolean) {
                return config.getBooleanFormat().format2Int((Boolean) obj).shortValue();
            }
            Double doubleValue;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            } else if (obj instanceof String) {
                doubleValue = config.getDoubleFormat().parse((String) obj);
            } else { // Date and other
                throw ExceptionConstant.getUnexpectedTypeEx("Short", obj.getClass().getName(), obj.toString());
            }
            short value = doubleValue.shortValue();
            if (doubleValue.doubleValue() != value) {
                // Make sure no precision was lost casting to 'short'.
                throw ExceptionConstant.getOutOfBoundsEx("Short", Double.toString(doubleValue));
            }
            return value;
        }
    };

    public static final TypeAdapter<Integer> INTEGER = new TypeAdapter<Integer>() {

        @Override
        public Integer read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Boolean) {
                return config.getBooleanFormat().format2Int((Boolean) obj);
            }
            Double doubleValue;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            } else if (obj instanceof String) {
                doubleValue = config.getDoubleFormat().parse((String) obj);
            } else { // Date and other
                throw ExceptionConstant.getUnexpectedTypeEx("Integer", obj.getClass().getName(), obj.toString());
            }
            int value = doubleValue.intValue();
            if (doubleValue.doubleValue() != value) {
                // Make sure no precision was lost casting to 'int'.
                throw ExceptionConstant.getOutOfBoundsEx("Integer", Double.toString(doubleValue));
            }
            return value;
        }
    };

    public static final TypeAdapter<Long> LONG = new TypeAdapter<Long>() {

        @Override
        public Long read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Boolean) {
                return config.getBooleanFormat().format2Int((Boolean) obj).longValue();
            }
            Double doubleValue;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            } else if (obj instanceof String) {
                doubleValue = config.getDoubleFormat().parse((String) obj);
            } else { // Date and other
                throw ExceptionConstant.getUnexpectedTypeEx("Long", obj.getClass().getName(), obj.toString());
            }
            long value = doubleValue.intValue();
            if (doubleValue.doubleValue() != value) {
                // Make sure no precision was lost casting to 'long'.
                throw ExceptionConstant.getOutOfBoundsEx("Long", Double.toString(doubleValue));
            }
            return value;
        }
    };

    public static final TypeAdapter<Character> CHARACTER = new TypeAdapter<Character>() {

        @Override
        public Character read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            String stringValue;
            if (obj instanceof String) {
                stringValue = (String) obj;
            } else { // Date, Boolean, Double and other
                throw ExceptionConstant.getUnexpectedTypeEx("Character", obj.getClass().getName(), obj.toString());
            }
            if (stringValue.length() != 1) {
                throw ExceptionConstant.getOutOfBoundsEx("Character", stringValue);
            }
            return stringValue.charAt(0);
        }
    };

    public static final TypeAdapter<Float> FLOAT = new TypeAdapter<Float>() {

        @Override
        public Float read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Boolean) {
                return config.getBooleanFormat().format2Int((Boolean) obj).floatValue();
            }
            Double doubleValue;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            } else if (obj instanceof String) {
                doubleValue = config.getDoubleFormat().parse((String) obj);
            } else { // Date and other
                throw ExceptionConstant.getUnexpectedTypeEx("Float", obj.getClass().getName(), obj.toString());
            }
            return doubleValue.floatValue();
        }
    };

    public static final TypeAdapter<Double> DOUBLE = new TypeAdapter<Double>() {

        @Override
        public Double read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Boolean) {
                return config.getBooleanFormat().format2Int((Boolean) obj).doubleValue();
            }
            Double doubleValue;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            } else if (obj instanceof String) {
                doubleValue = config.getDoubleFormat().parse((String) obj);
            } else { // Date and other
                throw ExceptionConstant.getUnexpectedTypeEx("Double", obj.getClass().getName(), obj.toString());
            }
            return doubleValue;
        }
    };

}
