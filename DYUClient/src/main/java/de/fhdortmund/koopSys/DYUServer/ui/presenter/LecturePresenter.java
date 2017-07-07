package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.service.LectureRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.View.LectureView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LectureListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@VaadinPresenter(viewName = LectureView.NAME)
public class LecturePresenter extends Presenter<LectureView> implements LectureListener {

	@Autowired
	LectureRestClient lectureClient;
	private Lecture lecture;
	
	@Override
	public void createNewLecture(Lecture lecture) {
		log.info("CreateLEcture");
		
		lectureClient.saveLecture(lecture);
		
		
		
	}

	public void setCurrentLecture(Lecture lecture) {
		this.lecture=(lectureClient.getLectureByOID(lecture.getOid()));
	
	}

	public Lecture getLecture() {
		return lecture;
	}

	
	
	
	
}
