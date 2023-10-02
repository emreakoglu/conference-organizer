package org.organizer.demo.model;

import java.util.List;

import org.organizer.demo.model.entity.Presentation;

import lombok.Data;

@Data
public class TrackDto {
	
	private List<Presentation> presentations;

}
