package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.events.annotation.EventBusListenerTopic;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.SessionManager;
import de.fhdortmund.koopSys.DYUServer.logic.entities.User;
import de.fhdortmund.koopSys.DYUServer.ui.Event.Event;
import de.fhdortmund.koopSys.DYUServer.ui.View.MainView;

/**
 * Presenter für den MainView Hauptaufgabe ist die Änderung des Views
 * 
 * @author droege_s
 *
 */
@VaadinPresenter(viewName = MainView.NAME)
public class MainPresenter extends Presenter<MainView> {

	private SessionManager sessionManager;
	private LoginPresenter loginPresenter;
	private DYUPresenter dyuPresenter;

	@Autowired
	public MainPresenter(SessionManager sessionManager, LoginPresenter loginPresenter, DYUPresenter dyuPresenter) {
		this.sessionManager = sessionManager;
		this.loginPresenter = loginPresenter;
		this.dyuPresenter = dyuPresenter;

	}

	/**
	 * Wird zum starten ausgeführt
	 */
	public void start() {
		if (sessionManager.isLoggedIn()) {
			showLobby();
		} else
			showLogin();
	}

	private void showLogin() {
		getView().setView(loginPresenter.getView());

	}

	private void showLobby() {
		// TODO Auto-generated method stub

	}

	@EventBusListenerTopic(topic = Event.LOGIN)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onLogin(User user) {
		sessionManager.setIdentity(user);

		showLobby();
	}

}
