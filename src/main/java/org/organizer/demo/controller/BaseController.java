package org.organizer.demo.controller;

import org.organizer.demo.exception.ResponseMatrix;
import org.organizer.demo.model.response.Response;

public abstract class BaseController {

	protected <T> Response<T> success(T entity) {
		Response<T> response = new Response<>();
		response.setResult(entity);
		response.setResponseMatrix(ResponseMatrix.SUCCESS);
		return response;
	}

}
