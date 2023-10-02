package org.organizer.demo.model.response;

import java.util.List;

import org.organizer.demo.model.PresentationsResponseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PresentationResponse {
	
	List<PresentationsResponseDto> presentations;
	
}
