package org.organizer.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PresentationsResponseDto {
	
	private Long id;
	
	private String name;
	
	private Long duration;

}
