package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.ui.View.LectureView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LectureListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@VaadinPresenter(viewName = LectureView.NAME)
public class LecturePresenter extends Presenter<LectureView> implements LectureListener {

	@Override
	public void createNewLecture(Lecture lecture) {
		// TODO Auto-generated method stub

	}

}
