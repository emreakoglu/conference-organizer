package org.organizer.demo.model.request;

import java.util.List;

import org.organizer.demo.exception.RequestValidationException;
import org.organizer.demo.model.PresentationDto;
import org.organizer.demo.utility.StringUtility;

import lombok.Data;

@Data
public class PresentationRequest implements Request {
	
	private Long conferenceId;
	
	private List<PresentationDto> presentations;

	@Override
	public void validate() throws RequestValidationException {
		if(null == conferenceId) {
			throw new RequestValidationException("Conference Id can not be empty");
		}
		
		if(presentations.isEmpty()) {
			throw new RequestValidationException("Presentations list can not be empty");
		} else {
			for (PresentationDto presentationDto : presentations) {
				if(StringUtility.isEmpty(presentationDto.getName())) {
					throw new RequestValidationException("Name can not be empty!");
				}
				
				if(null == presentationDto.getDuration() || !(presentationDto.getDuration().longValue() > 0)) {
					throw new RequestValidationException("Duration must be greather than 0!");
				} else if (presentationDto.getDuration() > 240) {
					throw new RequestValidationException("Duration can not be greather than 4 hours!");
				}
			}
		}

	}

}
