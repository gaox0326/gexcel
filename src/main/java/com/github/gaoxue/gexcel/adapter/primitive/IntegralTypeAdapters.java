package com.github.gaoxue.gexcel.adapter.primitive;

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
            Double doubleValue = null;
            try {
                doubleValue = reader.readDouble();
            } catch (NumberFormatException ex) {
                throw new ExcelParseException(ex);
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
            Double doubleValue = null;
            try {
                doubleValue = reader.readDouble();
            } catch (NumberFormatException ex) {
                throw new ExcelParseException(ex);
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
            Double doubleValue = null;
            try {
                doubleValue = reader.readDouble();
            } catch (NumberFormatException ex) {
                throw new ExcelParseException(ex);
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
            Double doubleValue = null;
            try {
                doubleValue = reader.readDouble();
            } catch (NumberFormatException ex) {
                throw new ExcelParseException(ex);
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
            String stringValue = reader.readString();
            if (stringValue == null) {
                return null;
            }
            if (stringValue.length() != 1) {
                throw new ExcelParseException("Expected a Character but was " + stringValue + ".");
            }
            return stringValue.charAt(0);
        }
    };

    public static final TypeAdapter<Float> FLOAT = new TypeAdapter<Float>() {

        public Float read(Reader reader) {
            Double doubleValue = reader.readDouble();
            if (doubleValue == null) {
                return null;
            }
            return doubleValue.floatValue();
        }
    };

    public static final TypeAdapter<Double> DOUBLE = new TypeAdapter<Double>() {

        public Double read(Reader reader) {
            return reader.readDouble();
        }
    };

}
