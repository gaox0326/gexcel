package com.github.gaoxue.gexcel.reader;

import java.util.Stack;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.github.gaoxue.gexcel.exception.ExcelParseException;

/**
 * Read value from excel cell.
 * <p>Value type
 * <ul>
 *   <li>{@code boolean}</li>
 *   <li>{@code Date}</li>
 *   <li>{@code double}</li>
 *   <li>{@code String}</li>
 * </ul>
 * @author gaoxue
 */
public abstract class AbstractExcelReader implements Reader {

    protected Workbook workbook;

    protected FormulaEvaluator evaluator;

    protected int sheetIndex;

    protected Sheet sheet;

    // inner use
    protected int currentRowIndex;

    protected int rowIndex;

    protected int lastRowIndex;

    protected Row row;

    protected int currentColIndex;

    protected int colIndex;

    protected int lastColIndex;

    protected ExcelToken peeked = ExcelToken.NONE;

    protected Stack<ExcelToken> peekedStack;

    public AbstractExcelReader(Workbook workbook) {
        this(workbook, 0);
    }

    public AbstractExcelReader(Workbook workbook, int sheetIndex) {
        if (workbook == null) {
            throw new ExcelParseException("workbook is null.");
        }
        this.workbook = workbook;
        this.sheetIndex = sheetIndex;
        init();
    }

    protected void init() {
        evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        sheet = workbook.getSheetAt(sheetIndex);
        lastRowIndex = sheet.getLastRowNum();
        peekedStack = new Stack<>();
        peekedStack.push(ExcelToken.NONE);
    }

    @Override
    public boolean hasNext() {
        return hasNextRow() || hasNextCell();
    }

    protected boolean hasNextCell() {
        if (peeked != ExcelToken.BEGIN_ROW) {
            // throw new ExcelParseException("Excepted BEGIN_ROW but was " +
            // peeked.toString());
            return false;
        }
        return colIndex <= lastColIndex;
    }

    protected boolean hasNextRow() {
        return rowIndex <= lastRowIndex;
    }

    protected void checkRowIndex() {
        if (rowIndex > lastRowIndex) {
            throw new ExcelParseException("Row index " + rowIndex + " is out of range: last row index " + lastRowIndex + ".");
        }
    }

    protected void checkColIndex() {
        if (colIndex > lastColIndex) {
            throw new ExcelParseException(
                    "Col index " + colIndex + " is out of range: row index " + currentRowIndex + ", last col index " + lastColIndex + ".");
        }
    }

    protected void beginRow() {
        checkRowIndex();
        currentRowIndex = rowIndex++;
        row = sheet.getRow(currentRowIndex);
        currentColIndex = -1;
        colIndex = 0;
        lastColIndex = row.getLastCellNum();
        peeked = ExcelToken.BEGIN_ROW;
    }

    protected void endRow() {
        colIndex = lastColIndex + 1;
        // clear current info
        peeked = ExcelToken.END_ROW;
    }

    protected abstract void peek(ExcelToken toPeek);

    @Override
    public void beginArray() {
        peek(ExcelToken.BEGIN_ARRAY);
    }

    @Override
    public void endArray() {
        peek(ExcelToken.END_ARRAY);
    }

    @Override
    public void beginObject() {
        peek(ExcelToken.BEGIN_OBJECT);
    }

    @Override
    public void endObject() {
        peek(ExcelToken.END_OBJECT);
    }

    @Override
    public String readString() {
        peek(ExcelToken.STRING);
        Cell cell = row.getCell(currentColIndex);
        return getStringValue(cell);
    }

    @Override
    public Boolean readBoolean() {
        peek(ExcelToken.BOOLEAN);
        Cell cell = row.getCell(currentColIndex);
        return getBooleanValue(cell);
    }

    @Override
    public Double readDouble() throws NumberFormatException {
        peek(ExcelToken.DOUBLE);
        Cell cell = row.getCell(currentColIndex);
        return getDoubleValue(cell);
    }

    @Override
    public Object readObject() {
        peek(ExcelToken.OBJECTVALUE);
        Cell cell = row.getCell(currentColIndex);
        return getObjectValue(cell);
    }

    @Override
    public void skip() {
        peek(ExcelToken.SKIP);
    }

    private String getStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        String value = null;
        CellValue cellValue = evaluator.evaluate(cell); // This evaluates a given cell, and returns the new value, without affecting the cell
        CellType cellType = cellValue.getCellTypeEnum();
        switch (cellType) {
        case STRING:
        case BLANK:
            value = cell.getStringCellValue();
            break;
        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                value = Format.format(cell.getDateCellValue());
            } else {
                value = Format.format(cell.getNumericCellValue());
            }
            break;
        case BOOLEAN:
            value = Format.format(cell.getBooleanCellValue());
            break;
        case FORMULA:
            // never happen
            break;
        case ERROR:
            cell.getErrorCellValue();
            // cell.getErrorCellValue();
            // FormulaError
        default:
            break;
        }
        return value;
    }

    private Boolean getBooleanValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        Boolean value = null;
        // CellType cellType = evaluator.evaluateFormulaCellEnum(cell);
        CellValue cellValue = evaluator.evaluate(cell);
        CellType cellType = cellValue.getCellTypeEnum();
        switch (cellType) {
        case BOOLEAN:
            value = cell.getBooleanCellValue();
            break;
        default:
            String stringValue = getStringValue(cell);
            value = Format.format2Boolean(stringValue);
            break;
        }
        return value;
    }

    private Double getDoubleValue(Cell cell) throws NumberFormatException {
        if (cell == null) {
            return null;
        }
        Double value = null;
        // CellType cellType = evaluator.evaluateFormulaCellEnum(cell);
        CellValue cellValue = evaluator.evaluate(cell);
        CellType cellType = cellValue.getCellTypeEnum();
        switch (cellType) {
        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                value = Format.format2Double(cell.getDateCellValue());
            } else {
                value = cell.getNumericCellValue();
            }
        default:
            String stringValue = getStringValue(cell);
            if (stringValue == null) {
                return null;
            }
            value = Format.format2Double(stringValue);
            break;
        }
        return value;
    }

    private Object getObjectValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        Object value = null;
        // CellType cellType = evaluator.evaluateFormulaCellEnum(cell);
        CellValue cellValue = evaluator.evaluate(cell);
        CellType cellType = cellValue.getCellTypeEnum();
        switch (cellType) {
        case STRING:
        case BLANK:
            value = cell.getStringCellValue();
            break;
        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                value = cell.getDateCellValue();
            } else {
                value = cell.getNumericCellValue();
            }
            break;
        case BOOLEAN:
            value = cell.getBooleanCellValue();
            break;
        case FORMULA:
            // never happen
            break;
        case ERROR:
            // cell.getErrorCellValue();
            // FormulaError
        default:
            break;
        }
        return value;
    }

}
