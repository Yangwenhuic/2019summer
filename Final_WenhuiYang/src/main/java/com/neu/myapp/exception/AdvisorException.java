package com.neu.myapp.exception;

public class AdvisorException extends Exception {
	public AdvisorException(String message) {
		super("AdvisorException-" + message);
	}

	public AdvisorException(String message, Throwable cause) {
		super("AdvisorException-" + message, cause);
	}
}
