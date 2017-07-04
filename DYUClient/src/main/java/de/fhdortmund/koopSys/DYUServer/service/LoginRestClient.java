package de.fhdortmund.koopSys.DYUServer.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import de.fhdortmund.koopSys.DYUServer.logic.entities.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginRestClient {

	private static final String SERVICE = "/user";

	private WebTarget target;

	@PostConstruct
	private void _init() {
		target = ClientBuilder.newClient().target(RestClientConfiguration.BASE_PATH + SERVICE);

	}

	public User create(User user) {
		log.info("creatUserRest");
		User responseUser = target.path("create").request().accept(MediaType.APPLICATION_JSON_VALUE)
				.put(Entity.json(user), User.class);
		log.info("Join User'{}'", responseUser);
		return responseUser;
	}

}
