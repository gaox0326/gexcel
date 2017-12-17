package com.github.gaoxue.gexcel.adapter.reference;

import java.text.ParseException;
import java.util.Date;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reader.Reader;

/**
 * {@code Date} adapter.
 * 
 * @author gaoxue
 */
public class DateAdapter implements TypeAdapter<Date> {

    public Date read(Reader reader) {
        Date date = null;
        try {
            date = reader.readDate();
        } catch (ParseException ex) {
            throw new ExcelParseException(ex);
        }
        return date;
    }

}
