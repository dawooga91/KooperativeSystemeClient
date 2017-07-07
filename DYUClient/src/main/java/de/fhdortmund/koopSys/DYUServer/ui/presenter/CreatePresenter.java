package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.service.LectureRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.View.CreateView;
import de.fhdortmund.koopSys.DYUServer.ui.View.LoginView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.CreateListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Presenter für den Login
 * 
 * @author droege_s
 *
 */
@Slf4j
@VaadinPresenter(viewName = LoginView.NAME)
public class CreatePresenter extends Presenter<CreateView> implements CreateListener {
	@Autowired
	LectureRestClient restClient;
	
	
	
	
	@Override
	public void creatLecture(Lecture lecture) {
		restClient.saveLecture(lecture);
		
	}


	

}
