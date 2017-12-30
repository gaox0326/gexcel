package com.github.gaoxue.gexcel.adapter.primitive;

import java.util.Date;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reader.Reader;

/**
 * Integral type adapters.
 * <p>
 * Handle {@code Byte} {@code byte}, {@code Short} {@code short},
 * {@code Integer} {@code int}, {@code Long} {@code long}, {@code Character}
 * {@code char}, {@code Double} {@code double}, {@code Float} {@code float}.
 * 
 * @author gaoxue
 */
public class IntegralTypeAdapters {

    public static final TypeAdapter<Byte> BYTE = new TypeAdapter<Byte>() {

        public Byte read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Date) {
                throw new ExcelParseException("Expected a Byte but was Date: " + obj + ".");
            }
            if (obj instanceof Boolean) {
                return (byte) ((boolean) obj ? 1 : 0);
            }
            Double doubleValue = null;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            }
            if (obj instanceof String) {
                doubleValue = Double.parseDouble((String) obj);
            }
            if (doubleValue == null) {
                return null;
            }
            byte value = doubleValue.byteValue();
            if (doubleValue.doubleValue() != value) {
                // Make sure no precision was lost casting to 'byte'.
                throw new ExcelParseException("Expected a Byte but was " + doubleValue + ".");
            }
            return value;
        }
    };

    public static final TypeAdapter<Short> SHORT = new TypeAdapter<Short>() {

        public Short read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Date) {
                throw new ExcelParseException("Expected a Short but was Date: " + obj + ".");
            }
            if (obj instanceof Boolean) {
                return (short) ((boolean) obj ? 1 : 0);
            }
            Double doubleValue = null;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            }
            if (obj instanceof String) {
                doubleValue = Double.parseDouble((String) obj);
            }
            if (doubleValue == null) {
                return null;
            }
            short value = doubleValue.shortValue();
            if (doubleValue.doubleValue() != value) {
                // Make sure no precision was lost casting to 'short'.
                throw new ExcelParseException("Expected a Short but was " + doubleValue + ".");
            }
            return value;
        }
    };

    public static final TypeAdapter<Integer> INTEGER = new TypeAdapter<Integer>() {

        public Integer read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Date) {
                throw new ExcelParseException("Expected a Integer but was Date: " + obj + ".");
            }
            if (obj instanceof Boolean) {
                return (boolean) obj ? 1 : 0;
            }
            Double doubleValue = null;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            }
            if (obj instanceof String) {
                doubleValue = Double.parseDouble((String) obj);
            }
            if (doubleValue == null) {
                return null;
            }
            int value = doubleValue.intValue();
            if (doubleValue.doubleValue() != value) {
                // Make sure no precision was lost casting to 'int'.
                throw new ExcelParseException("Expected a Integer but was " + doubleValue + ".");
            }
            return value;
        }
    };

    public static final TypeAdapter<Long> LONG = new TypeAdapter<Long>() {

        public Long read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Date) {
                throw new ExcelParseException("Expected a Long but was Date: " + obj + ".");
            }
            if (obj instanceof Boolean) {
                return (long) ((boolean) obj ? 1 : 0);
            }
            Double doubleValue = null;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            }
            if (obj instanceof String) {
                doubleValue = Double.parseDouble((String) obj);
            }
            if (doubleValue == null) {
                return null;
            }
            long value = doubleValue.intValue();
            if (doubleValue.doubleValue() != value) {
                // Make sure no precision was lost casting to 'long'.
                throw new ExcelParseException("Expected a Long but was " + doubleValue + ".");
            }
            return value;
        }
    };

    public static final TypeAdapter<Character> CHARACTER = new TypeAdapter<Character>() {

        public Character read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Date) {
                throw new ExcelParseException("Expected a Character but was Date: " + obj + ".");
            }
            if (obj instanceof Boolean) {
                throw new ExcelParseException("Expected a Character but was boolean: " + obj + ".");
            }
            if (obj instanceof Double) {
                throw new ExcelParseException("Expected a Character but was double: " + obj + ".");
            }
            String stringValue = (String) obj;
            if (stringValue.length() != 1) {
                throw new ExcelParseException("Expected a Character but was " + stringValue + ".");
            }
            return stringValue.charAt(0);
        }
    };

    public static final TypeAdapter<Float> FLOAT = new TypeAdapter<Float>() {

        public Float read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Date) {
                throw new ExcelParseException("Expected a Float but was Date: " + obj + ".");
            }
            if (obj instanceof Boolean) {
                return (float) ((boolean) obj ? 1 : 0);
            }
            Double doubleValue = null;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            }
            if (obj instanceof String) {
                doubleValue = Double.parseDouble((String) obj);
            }
            if (doubleValue == null) {
                return null;
            }
            return doubleValue.floatValue();
        }
    };

    public static final TypeAdapter<Double> DOUBLE = new TypeAdapter<Double>() {

        public Double read(Reader reader) {
            Object obj = reader.readObject();
            if (obj == null) {
                return null;
            }
            if (obj instanceof Date) {
                throw new ExcelParseException("Expected a Double but was Date: " + obj + ".");
            }
            if (obj instanceof Boolean) {
                return  (double) ((boolean) obj ? 1 : 0);
            }
            Double doubleValue = null;
            if (obj instanceof Double) {
                doubleValue = (Double) obj;
            }
            if (obj instanceof String) {
                doubleValue = Double.parseDouble((String) obj);
            }
            return doubleValue;
        }
    };

}
