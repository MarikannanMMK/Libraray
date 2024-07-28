package com.mmk.library.exception;

public class BookRequestNotFoundException extends RuntimeException {
    public BookRequestNotFoundException(String msg) {
        super(msg);
    }
}
