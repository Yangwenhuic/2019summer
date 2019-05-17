package com.neu.myapp.exception;

public class CourseException extends Exception{
	public CourseException(String message) {
		super("CourseException-" + message);
	}

	public CourseException(String message, Throwable cause) {
		super("CourseException-" + message, cause);
	}
}
