package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.events.annotation.EventBusListenerTopic;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import com.vaadin.ui.UI;

import de.fhdortmund.koopSys.DYUServer.logic.SessionManager;
import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
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
	private NewLecturePresenter newLecturePresenter;
	private LecturePresenter lecturePresenter;
	private AdminPresenter adminPresenter;

	@Autowired
	public MainPresenter(SessionManager sessionManager, LoginPresenter loginPresenter, LobbyPresenter lobbyPresenter,
			NewLecturePresenter newLecturePresenter, LecturePresenter lecturePresenter, AdminPresenter adminPresenter) {
		this.sessionManager = sessionManager;
		this.loginPresenter = loginPresenter;
		this.lobbyPresenter = lobbyPresenter;
		this.newLecturePresenter = newLecturePresenter;
		this.lecturePresenter = lecturePresenter;
		this.adminPresenter = adminPresenter;

	}

	@Autowired
	EventBus.ApplicationEventBus applicationEventBus;

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

	private void showLecture(Lecture lecture) {
		lecturePresenter.setCurrentLecture(lecture);

		getView().setView(lecturePresenter.getView());

	}

	private void showLogin() {
		log.info("open LoginView");
		getView().setView(loginPresenter.getView());

	}

	private void showLobby() {
		log.info("LobbyAufruf");
		getView().setView(lobbyPresenter.getView());

	}

	private void showLectureAdminView(Lecture lecture) {

		adminPresenter.setCurrentLecture(lecture);
		getView().setView(adminPresenter.getView());
	}

	@EventBusListenerTopic(topic = Event.LOGIN)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onLogin(User user) {
		log.info("try Login");
		sessionManager.setIdentity(user);

		showLobby();
	}

	@EventBusListenerTopic(topic = Event.JOIN)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onJoinLecture(Lecture lecture) {
		log.info("Join Lecture");
		lobbyPresenter.join(sessionManager.getIdentity(), lecture);

		showLecture(lecture);

	}

	@EventBusListenerTopic(topic = Event.CREATE_LECTURE)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onCreateAdminLec(String string) {
		log.info("CREAT_Lec");
		UI.getCurrent().addWindow(newLecturePresenter.getView());
	}

	@EventBusListenerTopic(topic = Event.CREATED_LECTURE)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onCreatedLecture(Lecture lecture) {
		log.info("Open AdminView");
		log.info(Long.toString(lecture.getOid()));
		showLectureAdminView(lecture);
	}

	@EventBusListenerTopic(topic = Event.DELETE_LECTURE)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onDeleteLecture(Lecture lecture) {
		log.info("kick out");
		showLobby();
		applicationEventBus.publish(Event.DELETE_LECTURE, lecture);
	}

	@EventBusListenerTopic(topic = Event.DELETE_LECTURE)
	@EventBusListenerMethod(scope = EventScope.APPLICATION)
	public void onAllDeleteLecture(Lecture lecture) {
		log.info("App info");
		// showLobby();
	}

}
