package org.organizer.demo.model.response;

import org.organizer.demo.exception.ResponseMatrix;

import lombok.Data;

@Data
public abstract class BaseResponse {

	private int responseCode = 200;

	private String responseMessage;

	private String responseType;
	
	public void setResponseMatrix(ResponseMatrix responseMatrix) {
		this.responseCode = responseMatrix.getCode();
		this.responseMessage = responseMatrix.getMessage();
		this.responseType = responseMatrix.getType();
	}

}
