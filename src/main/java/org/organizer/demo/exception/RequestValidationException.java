package org.organizer.demo.exception;

public class RequestValidationException extends Exception {

	private static final long serialVersionUID = -3510937825598010904L;

	public RequestValidationException(String errorMessage) {
		super(errorMessage);
	}

}
