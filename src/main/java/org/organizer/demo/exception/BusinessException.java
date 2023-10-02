package org.organizer.demo.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {

	private static final long serialVersionUID = 4576420223895039707L;

	private ResponseMatrix responseMatrix;

	public BusinessException(ResponseMatrix responseMatrix) {
		super(responseMatrix.getMessage());

		this.responseMatrix = responseMatrix;
	}

}
