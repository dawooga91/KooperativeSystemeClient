package de.fhdortmund.koopSys.DYUServer.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.springframework.stereotype.Component;

import de.fhdortmund.koopSys.DYUServer.logic.entities.User;

@Component
public class LoginRestClient {

	private static final String SERVICE = "/login";

	private WebTarget target;

	@PostConstruct
	private void _init() {
		target = ClientBuilder.newClient().target(RestClientConfiguration.BASE_PATH + SERVICE);

	}

	public void create(User user) {
		// TODO Auto-generated method stub

	}

}
