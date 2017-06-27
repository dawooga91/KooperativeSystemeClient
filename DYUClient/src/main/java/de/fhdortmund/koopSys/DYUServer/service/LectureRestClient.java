package de.fhdortmund.koopSys.DYUServer.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.springframework.stereotype.Component;

/**
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

}
