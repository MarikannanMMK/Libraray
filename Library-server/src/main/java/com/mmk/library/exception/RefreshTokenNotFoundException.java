package com.mmk.library.exception;

public class RefreshTokenNotFoundException extends RuntimeException{
    public RefreshTokenNotFoundException(String msg) {
        super(msg);
    }
}
