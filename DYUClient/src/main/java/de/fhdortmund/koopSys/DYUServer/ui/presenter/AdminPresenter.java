package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.service.LectureRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.View.AdminView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.AdminListener;

@VaadinPresenter(viewName = AdminView.NAME)
public class AdminPresenter extends Presenter<AdminView> implements AdminListener {

	@Autowired
	EventBus.SessionEventBus eventBus;

	@Autowired
	EventBus.ApplicationEventBus applicationEventBus;

	@Autowired
	private LectureRestClient lecClient;
	
	private Lecture currentLecture;

	@Override
	public Lecture getCurrentLecture() {
		
		return currentLecture;
	}

	@Override
	public void setCurrentLecture(Lecture lecture) {
		currentLecture = lecture;		
		System.out.println(currentLecture+"777777777777777777777777777777777777777777777777777777777777777");
	}

	@Override
	public void delete() {
		lecClient.remove(currentLecture);
		
	}

}
