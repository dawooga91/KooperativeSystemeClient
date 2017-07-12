package de.fhdortmund.koopSys.DYUServer.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

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

	public Boolean vote(Boolean bo,Lecture lec)
	{
		if(bo){
			Lecture lecture = target.path("vote/true/"+String.valueOf(lec.getOid())).request().accept(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(lec), Lecture.class);
	
		}else{
			Lecture lecture = target.path("vote/false/"+String.valueOf(lec.getOid())).request().accept(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(lec), Lecture.class);
		}
		return bo;
	}

	public Lecture remove(Lecture currentLecture) {
		Lecture responseLecture = target.path("delete"+String.valueOf(currentLecture)).request().accept(MediaType.APPLICATION_JSON_VALUE)
				.put(Entity.json(currentLecture), Lecture.class);
		log.info("delete Lecture '{}'", responseLecture);
		return responseLecture;
	}

}
