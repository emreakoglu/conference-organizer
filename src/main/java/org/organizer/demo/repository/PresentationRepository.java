package org.organizer.demo.repository;

import java.util.List;

import org.organizer.demo.model.entity.Conference;
import org.organizer.demo.model.entity.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
	
	List<Presentation> findByConference(Conference conference);

}
