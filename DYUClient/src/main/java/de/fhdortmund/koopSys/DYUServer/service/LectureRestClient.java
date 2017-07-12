package de.fhdortmund.koopSys.DYUServer.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import lombok.extern.slf4j.Slf4j;

/**
 * Client der auf die Rest-Schnistelle für Vorlesung zugreift
 * 
 * @author droege_s
 *
 */
@Slf4j
@Component
public class LectureRestClient {

	private static final String SERVICE = "/lecture";

	private WebTarget target;

	@PostConstruct
	private void _init() {
		target = ClientBuilder.newClient().target(RestClientConfiguration.BASE_PATH + SERVICE);
	}

	public Lecture getLectureByOID(long oid) {
		log.info("getLecture");
		Lecture lecture = target.path(String.valueOf(oid)).request().accept(MediaType.APPLICATION_JSON_VALUE)
				.get(Lecture.class);

		return lecture;
	}

	public List<Lecture> getAllLecture() {
		log.info("getAllLecture");
		List<Lecture> lectures = target.path("all").request().accept(MediaType.APPLICATION_JSON_VALUE)
				.get(new GenericType<List<Lecture>>() {
				});
		return lectures;
	}

	public Lecture saveLecture(Lecture lecture) {
		Lecture responseLecture = target.path("create").request().accept(MediaType.APPLICATION_JSON_VALUE)
				.put(Entity.json(lecture), Lecture.class);
		log.info("Save Lecture '{}'", responseLecture);
		return responseLecture;
	}

	public Lecture startNewPoll(long oid) {
		Lecture lecture = target.path("poll/new/" + String.valueOf(oid)).request()
				.accept(MediaType.APPLICATION_JSON_VALUE).get(Lecture.class);
		return lecture;

	}

	public Lecture getPoll(long oid) {
		Lecture lecture = target.path("poll/" + String.valueOf(oid)).request().accept(MediaType.APPLICATION_JSON_VALUE)
				.get(Lecture.class);
		return lecture;

	}

	public Boolean voteYes(Lecture lecture) {
		Boolean ret=target.path("poll/yes/"+String.valueOf(lecture.getOid())).request().accept(MediaType.APPLICATION_JSON_VALUE).get(Boolean.class);
		return ret;
	}

	public Boolean voteNo(Lecture lecture) {
		Boolean ret=target.path("poll/no/"+String.valueOf(lecture.getOid())).request().accept(MediaType.APPLICATION_JSON_VALUE).get(Boolean.class);
		return ret;
		
	}

}
