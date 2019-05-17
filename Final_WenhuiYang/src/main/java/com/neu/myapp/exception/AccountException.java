package com.neu.myapp.exception;

public class AccountException extends Exception {
	public AccountException(String message) {
		super("AccountException-" + message);
	}

	public AccountException(String message, Throwable cause) {
		super("AccountException-" + message, cause);
	}
}
