package org.organizer.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.organizer.demo.exception.BusinessException;
import org.organizer.demo.exception.ResponseMatrix;
import org.organizer.demo.model.TrackDto;
import org.organizer.demo.model.entity.Conference;
import org.organizer.demo.model.entity.Presentation;
import org.organizer.demo.model.entity.Track;
import org.organizer.demo.model.response.TrackResponse;
import org.organizer.demo.repository.ConferenceRepository;
import org.organizer.demo.repository.PresentationRepository;
import org.organizer.demo.repository.TrackRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackService {
	
	private final TrackRepository trackRepository;
	
	private final PresentationRepository presentationRepository;
	
	private final ConferenceRepository conferenceRepository;
	
	private final static Long AFTERNOON = 240L;
	
	private final static Long MORNING = 180L;
	
	private final static Random random = new Random();
	
	public TrackResponse generateTracks (Long conferenceId) throws BusinessException {
		Optional<Conference> conferenceOptional = conferenceRepository.findById(conferenceId);
		if(!conferenceOptional.isPresent()) {
			throw new BusinessException(ResponseMatrix.CONFERENCE_NOT_FOUND);
		}
		
		Conference conference = conferenceOptional.get();
		
		List<Presentation> presentations = presentationRepository.findByConference(conference);
		
		Long restDurationMorning = MORNING;
		Long restDurationAfternoon = AFTERNOON;
		
		List<Presentation> organizedMorningPresentations = new ArrayList<>();
		List<Presentation> organizedAfterNoonPresentations = new ArrayList<>();
		
		
		while (0 < presentations.size()) {
			
			final Long restDurationMorningFinal = restDurationMorning;
			Optional<Presentation> filledPresentationForMorning = presentations.stream().filter(presentation -> presentation.getDuration() == restDurationMorningFinal).findAny();
			if(filledPresentationForMorning.isPresent()) {
				organizedMorningPresentations.add(filledPresentationForMorning.get());
				presentations.remove(filledPresentationForMorning.get());
				restDurationMorning = restDurationMorning - filledPresentationForMorning.get().getDuration();
				if(presentations.size() > 0) {
					continue;
				}
			}
			
			final Long restDurationAfternoonFinal = restDurationAfternoon;
			Optional<Presentation> filledPresentationForAfternoon = presentations.stream().filter(presentation -> presentation.getDuration() == restDurationAfternoonFinal).findAny();
			if(filledPresentationForAfternoon.isPresent()) {
				organizedAfterNoonPresentations.add(filledPresentationForAfternoon.get());
				presentations.remove(filledPresentationForAfternoon.get());
				restDurationAfternoon = restDurationAfternoon - filledPresentationForAfternoon.get().getDuration();
				if(presentations.size() > 0) {
					continue;
				}
			}
			
			Long minDurationInList = 0L;
			if(presentations.size() > 0) {
				minDurationInList = presentations.stream().min(Comparator.comparing(Presentation::getDuration)).orElseThrow().getDuration();
			}
			
			if((restDurationMorning < minDurationInList && restDurationAfternoon < minDurationInList) || presentations.size() == 0) {
				List<Presentation> allOrganizedPresentations = new ArrayList<>();
				allOrganizedPresentations.addAll(organizedMorningPresentations);
				Presentation lunch = PresentationService.lunchBreak(conference);
				presentationRepository.save(lunch);
				allOrganizedPresentations.add(lunch);
				allOrganizedPresentations.addAll(organizedAfterNoonPresentations);
				Track track = new Track();
				track.setConference(conference);
				track.setPresentations(allOrganizedPresentations);
				trackRepository.save(track);
				//TODO save the current Track and create new one
				restDurationMorning = MORNING;
				restDurationAfternoon = AFTERNOON;
				organizedMorningPresentations = new ArrayList<>();
				organizedAfterNoonPresentations = new ArrayList<>();
				continue;
			}
			
			Presentation nextPresentation = presentations.get(random.nextInt(presentations.size()));
			
			if(nextPresentation.getDuration().compareTo(restDurationMorning) <= 0) {
				organizedMorningPresentations.add(nextPresentation);
				restDurationMorning = restDurationMorning - nextPresentation.getDuration();
				presentations.remove(nextPresentation);
			} else if (nextPresentation.getDuration().compareTo(restDurationAfternoon) <= 0){
				organizedAfterNoonPresentations.add(nextPresentation);
				restDurationAfternoon = restDurationAfternoon - nextPresentation.getDuration();
				presentations.remove(nextPresentation);
			}
			
		}
		
		TrackResponse response = retrieveTracks(conferenceId);
		
		return response;
	}
	
	public TrackResponse retrieveTracks(Long conferenceId) throws BusinessException {
		Optional<Conference> conferenceOptional = conferenceRepository.findById(conferenceId);
		if(!conferenceOptional.isPresent()) {
			throw new BusinessException(ResponseMatrix.CONFERENCE_NOT_FOUND);
		}
		
		Conference conference = conferenceOptional.get();
		List<TrackDto> trackDtos = new ArrayList<>();
		
		List<Track> allTracks = trackRepository.findByConference(conference);
		for (Track track : allTracks) {
			TrackDto trackDto = new TrackDto();
			trackDto.setPresentations(track.getPresentations());
			Long existingTotalPresentationsDuration = track.getPresentations().stream().map(Presentation::getDuration).reduce(0L, Long::sum);
			if (existingTotalPresentationsDuration < (MORNING + AFTERNOON)) {
				trackDto.getPresentations().add(PresentationService.eventPresentation());
			}
			trackDtos.add(trackDto);
		}
		
		TrackResponse response = TrackResponse.builder().conferenceName(conference.getName()).conferenceDate(conference.getDate()).tracks(trackDtos).build();
		
		return response;
		
		
	}

}
