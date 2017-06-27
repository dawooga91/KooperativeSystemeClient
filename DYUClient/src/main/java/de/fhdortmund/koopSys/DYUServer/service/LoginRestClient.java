package de.fhdortmund.koopSys.DYUServer.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.springframework.stereotype.Component;

@Component
public class LoginRestClient {

	private static final String SERVICE = "/login";

	private WebTarget target;

	@PostConstruct
	private void _init() {
		target = ClientBuilder.newClient().target(RestClientConfiguration.BASE_PATH + SERVICE);

	}

}
