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
import lombok.extern.slf4j.Slf4j;

/**
 * Presenter für den MainView Hauptaufgabe ist die Änderung des Views
 * 
 * @author droege_s
 * @param <lobbyPresenter>
 *
 */
@Slf4j
@VaadinPresenter(viewName = MainView.NAME)
public class MainPresenter extends Presenter<MainView> {

	private SessionManager sessionManager;
	private LoginPresenter loginPresenter;
	private LobbyPresenter lobbyPresenter;

	@Autowired
	public MainPresenter(SessionManager sessionManager, LoginPresenter loginPresenter, LobbyPresenter lobbyPresenter) {
		this.sessionManager = sessionManager;
		this.loginPresenter = loginPresenter;
		this.lobbyPresenter = lobbyPresenter;

	}

	/**
	 * Wird zum starten ausgeführt
	 */
	public void start() {
		log.info("start App");
		if (sessionManager.isLoggedIn()) {
			log.info("alreday Login");
			showLobby();
		} else
			showLogin();
	}

	private void showLogin() {
		log.info("open LoginView");
		getView().setView(loginPresenter.getView());

	}

	private void showLobby() {
		System.err.println("LobbyAufruf");
		getView().setView(lobbyPresenter.getView());

	}

	@EventBusListenerTopic(topic = Event.LOGIN)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onLogin(User user) {
		log.info("try Login");
		sessionManager.setIdentity(user);

		showLobby();
	}

}
