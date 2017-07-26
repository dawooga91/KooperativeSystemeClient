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
import de.fhdortmund.koopSys.DYUServer.logic.entities.User;
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
		log.info(Long.toString(responseLecture.getOid()));
		return responseLecture;
	}

	public Lecture getPoll(long oid) {
		log.info("Poll der Vorlesung{}", getLectureByOID(oid).getPoll());

		return getLectureByOID(oid);

	}

	public Lecture startNewPoll(long oid) {
		Lecture lectureByOID = getLectureByOID(oid);
		return target.path("poll/new/" + String.valueOf(oid)).request().accept(MediaType.APPLICATION_JSON_VALUE)
				.put(Entity.json(lectureByOID), Lecture.class);

	}

	public Boolean vote(Boolean bo, Lecture lec) {
		Lecture lecture;
		if (bo) {
			lecture = target.path("vote/true/" + String.valueOf(lec.getOid())).request()
					.accept(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(lec), Lecture.class);

		} else {
			lecture = target.path("vote/false/" + String.valueOf(lec.getOid())).request()
					.accept(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(lec), Lecture.class);
		}
		return bo;
	}

	public Lecture remove(Lecture currentLecture) {
		log.info(Long.toString(currentLecture.getOid()));
		target.path("delete/" + String.valueOf(currentLecture.getOid())).request().delete();
		log.info("delete Lecture '{}'", currentLecture);
		return currentLecture;
	}

	public void join(User identity, Lecture lec) {
		target.path("join/" + String.valueOf(identity.getOid()) + "/" + String.valueOf(lec.getOid())).request()
				.accept(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(lec), Lecture.class);
		log.info("Join User '{}'", identity);
	}

	public void close(long oid) {
		target.path("close/" + String.valueOf(oid)).request().accept(MediaType.APPLICATION_JSON_VALUE)
				.put(Entity.json(oid), Long.class);

	}

	public void askQuestion(long oid, String question) {
		target.path("question/" + String.valueOf(oid) + "/" + question).request()
				.accept(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(oid), Long.class);

	}

}
