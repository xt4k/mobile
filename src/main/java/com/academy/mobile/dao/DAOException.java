package com.academy.mobile.dao;

public class DAOException extends RuntimeException {
    public DAOException(String message, Throwable e) {
        super(message, e);
    }

    public DAOException(String message) {
        super(message);
    }
}
