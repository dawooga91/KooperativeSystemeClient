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
	private static final String NEWLECTURE = "NEW_LECTURE";
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
	private LecturePresenter lecturePresenter;
	@Autowired
	private AdminPresenter adminPresenter;
	@Autowired
	private NewLecturePresenter newLecturePresenter;

	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this, this);

		log.info("Starting UI");
		navigator.addView(LOBBY, lobbyPresenter.getView());
		navigator.addView(LOGIN, loginPresenter.getView());
		navigator.addView(NEWLECTURE, newLecturePresenter.getView());
		//navigator.addView(ADMIN, adminPresenter.getView());
		navigator.addView(LECTURE, lecturePresenter.getView());
		start();

	}

	private void start() {
		if (sessionManager.isLoggedIn())
			navigator.navigateTo(LOBBY);
		else
			navigator.navigateTo(LOGIN);

	}

	

	

	

	

}
