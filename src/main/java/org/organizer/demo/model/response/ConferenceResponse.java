package org.organizer.demo.model.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConferenceResponse {
	
	private Long id;
	
	private String name;
	
	private LocalDate date;

}
