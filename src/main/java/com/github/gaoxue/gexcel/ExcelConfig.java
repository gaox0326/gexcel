package com.github.gaoxue.gexcel;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reader.ExcelReader;

/**
 * @author gaoxue
 */
public class ExcelConfig {

    private Workbook workbook;

    private int sheetIndex = 0;

    private int titleRowIndex = 0;

    private int dataRowIndex = 1;

    /** date format pattern */
    private String dateFormat;

    public ExcelConfig(InputStream inputStream) throws ExcelParseException {
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (EncryptedDocumentException | InvalidFormatException | IOException ex) {
            throw new ExcelParseException(ex);
        }
    }

    public ExcelConfig(InputStream inputStream, int sheetIndex, int titleRowIndex, int dataRowIndex) {
        this(inputStream);
        this.sheetIndex = sheetIndex;
        this.titleRowIndex = titleRowIndex;
        this.dataRowIndex = dataRowIndex;
    }

    public ExcelReader getReader() {
        return new ExcelReader(workbook, sheetIndex, titleRowIndex, dataRowIndex);
    }

    /**
     * get date format pattern
     * @return date format pattern
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * set date format pattern
     * @param dateFormat date format pattern
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
