package com.github.chenhao96.utils.exception;

public class JdbcSqlException extends RuntimeException {

    public JdbcSqlException(Throwable throwable) {
        super(throwable);
    }

    public JdbcSqlException(String message) {
        super(message);
    }
}
