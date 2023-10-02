package org.organizer.demo.model.request;

import org.organizer.demo.exception.RequestValidationException;

public interface Request {
	
	void validate() throws RequestValidationException;

}
