package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.entities.User;
import de.fhdortmund.koopSys.DYUServer.service.LoginRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.Event.Event;
import de.fhdortmund.koopSys.DYUServer.ui.View.LoginView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LoginListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Presenter für den Login
 * 
 * @author droege_s
 *
 */
@Slf4j
@VaadinPresenter(viewName = LoginView.NAME)
public class LoginPresenter extends Presenter<LoginView> implements LoginListener {

	@Autowired
	LoginRestClient loginClient;
	
	@Autowired
	private EventBus.SessionEventBus eventBus;

	@Override
	public void login(User user) {

		

		
		log.info("login");
		loginClient.create(user);
		log.info("publish LoginEvent");
		eventBus.publish(Event.LOGIN, this, user);
	}

}
