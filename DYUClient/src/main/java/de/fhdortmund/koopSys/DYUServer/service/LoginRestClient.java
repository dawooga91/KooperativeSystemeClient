package de.fhdortmund.koopSys.DYUServer.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.springframework.http.MediaType;
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

	public User create(User user) {
		User responseUser = target.path("create").request().accept(MediaType.APPLICATION_JSON_VALUE)
				.put(Entity.json(user), User.class);
		return responseUser;
	}

}
