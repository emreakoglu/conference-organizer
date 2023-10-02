package org.organizer.demo.model.request;

import java.time.LocalDate;

import org.organizer.demo.exception.RequestValidationException;
import org.organizer.demo.utility.StringUtility;

import lombok.Data;

@Data
public class ConferenceRequest implements Request {
	
	private String name;
	
	private LocalDate date;

	@Override
	public void validate() throws RequestValidationException {
		if(StringUtility.isEmpty(name)) {
			throw new RequestValidationException("Name can not be empty!");
		}
		if(null == date) {
			throw new RequestValidationException("Date can not be empty!");
		}

	}

}
