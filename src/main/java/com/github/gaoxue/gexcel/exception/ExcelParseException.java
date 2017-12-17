package com.github.gaoxue.gexcel.exception;

/**
 * @author gaoxue
 */
public class ExcelParseException extends RuntimeException {

    private static final long serialVersionUID = 4593140433938554389L;

    public ExcelParseException(String message) {
        super(message);
    }

    public ExcelParseException(Throwable throwable) {
        super(throwable);
    }

    public ExcelParseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
