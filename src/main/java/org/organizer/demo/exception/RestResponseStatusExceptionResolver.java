package org.organizer.demo.exception;

import org.organizer.demo.model.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseStatusExceptionResolver extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { RequestValidationException.class })
	protected ResponseEntity<Object> handleRequestValidationException(RequestValidationException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setResponseMessage(ex.getMessage());
		errorResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());

		logger.error(ex);

		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = { BusinessException.class })
	protected ResponseEntity<Object> handleBusinessValidationException(BusinessException ex, WebRequest request) {
		ResponseMatrix responseMatrix = ex.getResponseMatrix();

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setResponseMatrix(responseMatrix);
		errorResponse.setResponseMessage(ex.getMessage());

		logger.error(ex);

		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
