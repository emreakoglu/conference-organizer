package org.organizer.demo.model.response;

import java.time.LocalDate;
import java.util.List;

import org.organizer.demo.model.TrackDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackResponse {
	
	private String conferenceName;
	
	private LocalDate conferenceDate;
	
	private List<TrackDto> tracks;

}
