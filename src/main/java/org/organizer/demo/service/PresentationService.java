package org.organizer.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.organizer.demo.exception.BusinessException;
import org.organizer.demo.exception.ResponseMatrix;
import org.organizer.demo.model.PresentationsResponseDto;
import org.organizer.demo.model.entity.Conference;
import org.organizer.demo.model.entity.Presentation;
import org.organizer.demo.model.request.PresentationRequest;
import org.organizer.demo.model.response.PresentationResponse;
import org.organizer.demo.repository.ConferenceRepository;
import org.organizer.demo.repository.PresentationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresentationService {
	
	private final PresentationRepository presentationRepository;
	
	private final ConferenceRepository conferenceRepository;
	
	public PresentationResponse createPresentation(PresentationRequest request) throws BusinessException {
		Optional<Conference> conferenceOptional = conferenceRepository.findById(request.getConferenceId());
		if(!conferenceOptional.isPresent()) {
			throw new BusinessException(ResponseMatrix.CONFERENCE_NOT_FOUND);
		}
		
		List<Presentation> presentations = request.getPresentations().stream().map(presentation -> new Presentation(null, presentation.getName(), presentation.getDuration(), conferenceOptional.get())).collect(Collectors.toList());
		presentations = presentationRepository.saveAll(presentations);
		List<PresentationsResponseDto> presentationDtos = presentations.stream().map(presentation -> new PresentationsResponseDto(presentation.getId(), presentation.getName(), presentation.getDuration())).collect(Collectors.toList());
		PresentationResponse response = PresentationResponse.builder().presentations(presentationDtos).build();
		return response;
	}
	
	@Bean()
	@Scope("singleton")
	public static Presentation eventPresentation() {
		Presentation networkingEvent = new Presentation(null, "Networking Event", 0L, null);
		return networkingEvent;
	}
	
	public static Presentation lunchBreak(Conference conference) {
		Presentation lunch = new Presentation(null, "Lunch", 60L, conference);
		lunch.setConference(conference);
		return lunch;
	}

}
