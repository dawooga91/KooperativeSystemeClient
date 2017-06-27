package de.fhdortmund.koopSys.DYUServer.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;

/**
 * Client der auf die Rest-Schnistelle für Vorlesung zugreift
 * 
 * @author droege_s
 *
 */
@Component
public class LectureRestClient {

	private static final String SERVICE = "/lecture";

	private WebTarget target;

	@PostConstruct
	private void _init() {
		target = ClientBuilder.newClient().target(RestClientConfiguration.BASE_PATH + SERVICE);
	}

	public Lecture getLectureByOID(long oid) {
		Lecture lecture = target.path(String.valueOf(oid)).request().accept(MediaType.APPLICATION_JSON_VALUE)
				.get(Lecture.class);

		return lecture;
	}

	public List<Lecture> getAllLecture() {
		List<Lecture> lectures = target.path("all").request().accept(MediaType.APPLICATION_JSON_VALUE)
				.get(new GenericType<List<Lecture>>() {
				});
		return lectures;
	}
}
