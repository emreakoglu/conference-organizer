package org.organizer.demo.service;

import org.organizer.demo.model.entity.Conference;
import org.organizer.demo.model.request.ConferenceRequest;
import org.organizer.demo.model.response.ConferenceResponse;
import org.organizer.demo.repository.ConferenceRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConferenceService {
	
	private final ConferenceRepository conferenceRepository;
	
	public ConferenceResponse createConference(ConferenceRequest request) {
		Conference conference = new Conference();
		conference.setDate(request.getDate());
		conference.setName(request.getName());
		conference = conferenceRepository.save(conference);
		ConferenceResponse response = ConferenceResponse.builder().date(conference.getDate()).id(conference.getId()).name(conference.getName()).build();
		return response;
	}

}
