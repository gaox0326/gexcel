package com.github.gaoxue.gexcel.reader;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.template.Metedata;

/**
 * @author gaoxue
 */
public class ExcelReader extends AbstractExcelReader {

    private int titleRowIndex;

    private Map<Integer, Metedata> metedataMap;

    private int dataRowIndex;

    public ExcelReader(Workbook workbook) {
        this(workbook, 0, 0, 1);
    }

    public ExcelReader(Workbook workbook, int sheetIndex, int titleRowIndex, int dataRowIndex) {
        super(workbook, sheetIndex);
        this.titleRowIndex = titleRowIndex;
        this.dataRowIndex = dataRowIndex;
        initMetedata();
    }

    private void initMetedata() {
        metedataMap = new HashMap<>();
        rowIndex = titleRowIndex;
        beginObject();
        while (hasNext()) {
            Object value = readObject();
            if (value == null) {
                continue;
            }
            Metedata metedata = new Metedata();
            metedata.setName(value.toString());
            metedata.setIndex(currentColIndex);
            metedataMap.put(currentColIndex, metedata);
        }
        endObject();
    }

    @Override
    public boolean hasNext() {
        ExcelToken peedked = peekedStack.peek();
        if (peedked == ExcelToken.BEGIN_OBJECT) {
            return hasNextCell();
        } else if (peedked == ExcelToken.BEGIN_ARRAY) {
            return hasNextRow();
        }
        return super.hasNext();
    }

    @Override
    public Metedata readNextMeteData() {
        peek(ExcelToken.METEDATA);
        if (!metedataMap.containsKey(colIndex)) {
            return null;
        }
        return metedataMap.get(colIndex);
    }

    protected void peek(ExcelToken toPeek) {
        if (toPeek == ExcelToken.BEGIN_ARRAY) {
            checkRowIndex();
            if (rowIndex > dataRowIndex) {
                throw new ExcelParseException("Row index " + rowIndex + " exceeds data row index " + dataRowIndex + ".");
            }
            rowIndex = dataRowIndex;
            peekedStack.push(toPeek);
        } else if (toPeek == ExcelToken.END_ARRAY) {
            if (peekedStack.peek() != ExcelToken.BEGIN_ARRAY) {
                throw new ExcelParseException("Expected BEGIN_ARRAY before END_ARRAY.");
            }
            peekedStack.pop();
            rowIndex = lastRowIndex + 1;
        } else if (toPeek == ExcelToken.BEGIN_OBJECT) {
            beginRow();
            peekedStack.push(toPeek);
        } else if (toPeek == ExcelToken.END_OBJECT) {
            if (peekedStack.peek() != ExcelToken.BEGIN_OBJECT) {
                throw new ExcelParseException("Expected BEGIN_OBJECT before END_OBJECT.");
            }
            peekedStack.pop();
            endRow();
        } else if (toPeek == ExcelToken.METEDATA) {
            checkColIndex();
        } else if (toPeek == ExcelToken.OBJECTVALUE) {
            checkColIndex();
            currentColIndex = colIndex++;
        } else if (toPeek == ExcelToken.SKIP) {
            if (peekedStack.peek() == ExcelToken.BEGIN_ARRAY) {
                rowIndex++;
            } else if (peekedStack.peek() == ExcelToken.BEGIN_OBJECT) {
                colIndex++;
            }
        }
    }

}
