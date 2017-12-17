package com.github.gaoxue.gexcel.reader;

import java.text.ParseException;
import java.util.Date;

import com.github.gaoxue.gexcel.template.Metedata;

/**
 * @author gaoxue
 */
public interface Reader {

    void beginArray();

    void endArray();

    void beginObject();

    void endObject();

    boolean hasNext();

    Metedata readNextMeteData();

    String readString();

    Boolean readBoolean();

    Double readDouble() throws NumberFormatException;

    Date readDate() throws ParseException;

    Object readObject();

    void skip();

    // isNextNull()
}
