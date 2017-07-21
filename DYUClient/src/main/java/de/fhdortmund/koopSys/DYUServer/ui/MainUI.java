package de.fhdortmund.koopSys.DYUServer.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.events.annotation.EventBusListenerTopic;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import de.fhdortmund.koopSys.DYUServer.logic.SessionManager;
import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.logic.entities.User;
import de.fhdortmund.koopSys.DYUServer.ui.presenter.AdminPresenter;
import de.fhdortmund.koopSys.DYUServer.ui.presenter.LecturePresenter;
import de.fhdortmund.koopSys.DYUServer.ui.presenter.LobbyPresenter;
import de.fhdortmund.koopSys.DYUServer.ui.presenter.LoginPresenter;
import de.fhdortmund.koopSys.DYUServer.ui.presenter.NewLecturePresenter;
import lombok.extern.slf4j.Slf4j;

/**
 * MainUi der Anwendung
 * 
 * @author droege_s
 *
 */
@Slf4j
@SpringUI(path = "/")
@Title("DYU-App")
@Theme("default")
public class MainUI extends UI {
	private static final String LECTURE = "LECTURE";
	private static final String ADMIN = "ADMIN";
	private static final String LOGIN = "LOGIN";
	private static final String LOBBY = "LOBBY";
	Navigator navigator;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionManager sessionManager;
	@Autowired
	private LoginPresenter loginPresenter;
	@Autowired
	private LobbyPresenter lobbyPresenter;
	@Autowired
	private NewLecturePresenter newLecturePresenter;
	@Autowired
	private LecturePresenter lecturePresenter;
	@Autowired
	private AdminPresenter adminPresenter;
	@Autowired
	EventBus.ApplicationEventBus applicationEventBus;

	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this, this);

		log.info("Starting UI");
		navigator.addView(LOBBY, lobbyPresenter.getView());
		navigator.addView(LOGIN, loginPresenter.getView());
		start();

	}

	private void start() {
		if (sessionManager.isLoggedIn())
			navigator.navigateTo(LOBBY);
		else
			navigator.navigateTo(LOGIN);

	}

	private void showLecture(Lecture lecture) {
		lecturePresenter.setCurrentLecture(lecture);
		navigator.navigateTo(LECTURE);
	}

	private void showLogin() {
		navigator.navigateTo(LOGIN);
	}

	private void showLobby() {
		navigator.navigateTo(LOBBY);
	}

	private void showLectureAdminView(Lecture lecture) {
		navigator.navigateTo(ADMIN);
	}

	@EventBusListenerTopic(topic = de.fhdortmund.koopSys.DYUServer.ui.Event.Event.LOGIN)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onLogin(User user) {
		log.info("try Login");
		sessionManager.setIdentity(user);

		showLobby();
	}

	@EventBusListenerTopic(topic = de.fhdortmund.koopSys.DYUServer.ui.Event.Event.JOIN)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onJoinLecture(Lecture lecture) {
		log.info("Join Lecture");
		lobbyPresenter.join(sessionManager.getIdentity(), lecture);

		showLecture(lecture);

	}

	@EventBusListenerTopic(topic = de.fhdortmund.koopSys.DYUServer.ui.Event.Event.CREATE_LECTURE)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onCreateAdminLec(String string) {
		log.info("CREAT_Lec");
		UI.getCurrent().addWindow(newLecturePresenter.getView());
	}

	@EventBusListenerTopic(topic = de.fhdortmund.koopSys.DYUServer.ui.Event.Event.CREATED_LECTURE)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onCreatedLecture(Lecture lecture) {
		log.info("Open AdminView");
		log.info(Long.toString(lecture.getOid()));
		showLectureAdminView(lecture);
		applicationEventBus.publish(de.fhdortmund.koopSys.DYUServer.ui.Event.Event.CREATED_LECTURE, this, "Refresh");
	}

	@EventBusListenerTopic(topic = de.fhdortmund.koopSys.DYUServer.ui.Event.Event.DELETE_LECTURE)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onDeleteLecture(Lecture lecture) {
		log.info("kick out");
		showLobby();
		applicationEventBus.publish(de.fhdortmund.koopSys.DYUServer.ui.Event.Event.DELETE_LECTURE, this, lecture);
	}

	@EventBusListenerTopic(topic = de.fhdortmund.koopSys.DYUServer.ui.Event.Event.DELETE_LECTURE)
	@EventBusListenerMethod(scope = EventScope.APPLICATION)
	public void onAllDeleteLecture(Lecture lecture) {
		log.info("App info");
		// TODO
	}

}
