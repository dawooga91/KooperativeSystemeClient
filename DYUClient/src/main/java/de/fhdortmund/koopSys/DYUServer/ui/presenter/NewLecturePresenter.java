package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.events.annotation.EventBusListenerTopic;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import com.vaadin.ui.UI;

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
	EventBus.SessionEventBus eventBus;

	@Override
	public void createLecture(Lecture lecture) {
		eventBus.publish(Event.CREATE_LECTURE, this, lecture);

	}

	@EventBusListenerTopic(topic = Event.CREATE_LECTURE)
	@EventBusListenerMethod(scope = EventScope.SESSION)
	public void onCreatedLecture(Lecture lec) {
		log.info("create new Lecture");
		// getView().clearInput();
		UI.getCurrent().addWindow(getView());

	}

}
