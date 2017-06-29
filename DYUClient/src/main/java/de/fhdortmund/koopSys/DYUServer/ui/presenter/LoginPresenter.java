package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.Presenter;

import de.fhdortmund.koopSys.DYUServer.logic.entities.User;
import de.fhdortmund.koopSys.DYUServer.service.LoginRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.Event.Event;
import de.fhdortmund.koopSys.DYUServer.ui.View.LoginView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LoginListener;

/**
 * Presenter für den Login
 * 
 * @author droege_s
 *
 */
public class LoginPresenter extends Presenter<LoginView> implements LoginListener {

	@Autowired
	LoginRestClient loginClient;

	@Autowired
	private EventBus.SessionEventBus eventBus;

	@Override
	public void login(User user) {
		loginClient.create(user);
		eventBus.publish(Event.LOGIN, this, user);
	}

}
