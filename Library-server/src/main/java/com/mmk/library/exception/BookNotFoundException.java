package com.mmk.library.exception;

public class BookNotFoundException extends RuntimeException {



	public BookNotFoundException(String msg) {
		super(msg);
	}
}