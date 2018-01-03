package com.github.gaoxue.gexcel.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reader.ExcelReader;

/**
 * Excel configurations.
 * 
 * @author gaoxue
 */
public class ExcelConfig {

    /** excel object */
    private Workbook workbook;

    /** sheet index to convert */
    private int sheetIndex = 0;

    /** title row index */
    private int titleRowIndex = 0;

    /** data row begin index */
    private int dataRowIndex = 1;

    /**
     * Get excel config with excel input stream
     * <p>Default values(0 based): sheet index is 0, title row index is 0, data row index is 1
     * @param inputStream excel input stream
     * @throws ExcelParseException if create {@link Workbook} failed
     */
    public ExcelConfig(InputStream inputStream) throws ExcelParseException {
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (EncryptedDocumentException | InvalidFormatException | IOException ex) {
            throw new ExcelParseException(ex);
        }
    }

    /**
     * Get excel config with excel input stream, sheet index, title row index, data row index
     * @param inputStream excel input stream
     * @param sheetIndex sheet index
     * @param titleRowIndex title row index
     * @param dataRowIndex data row index
     */
    public ExcelConfig(InputStream inputStream, int sheetIndex, int titleRowIndex, int dataRowIndex) {
        this(inputStream);
        this.sheetIndex = sheetIndex;
        this.titleRowIndex = titleRowIndex;
        this.dataRowIndex = dataRowIndex;
    }

    /**
     * Get {@link ExcelReader} instance with this excel config
     * @return
     */
    public ExcelReader getReader() {
        return new ExcelReader(workbook, sheetIndex, titleRowIndex, dataRowIndex);
    }

}
