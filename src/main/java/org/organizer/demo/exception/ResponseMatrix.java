package org.organizer.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMatrix {

	SUCCESS(200, "Success", "OK"),
	CONFERENCE_NOT_FOUND(500, "Conference is not found!", "Error");

	private final int code;
	private final String message;
	private final String type;

}
