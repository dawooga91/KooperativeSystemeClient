package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.SessionManager;
import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.service.LectureRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.Event.Event;
import de.fhdortmund.koopSys.DYUServer.ui.View.NewLectureView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.NewLectureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Eingabemaske zum Anlegen einer Vorlesung
 * 
 * @author droege_s
 */
@Slf4j
@VaadinPresenter(viewName = NewLectureView.NAME)
public class NewLecturePresenter extends Presenter<NewLectureView> implements NewLectureListener {

	@Autowired
	LectureRestClient lectureClient;
	
	@Autowired
	SessionManager sessionManager;

	@Autowired	
	EventBus.SessionEventBus eventBus;

	@Override
	public void createLecture(Lecture lecture) {
		log.info("Try to creat lec");
		lecture.setAdmin(sessionManager.getIdentity());
		
		lectureClient.saveLecture(lecture);
		eventBus.publish(Event.CREATE_LECTURE, this, lecture);

	}

	

}
