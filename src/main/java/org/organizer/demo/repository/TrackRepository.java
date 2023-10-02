package org.organizer.demo.repository;

import java.util.List;

import org.organizer.demo.model.entity.Conference;
import org.organizer.demo.model.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
	
	List<Track> findByConference(Conference conference);

}
