package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.service.LectureRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.View.AdminView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.AdminListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@VaadinPresenter(viewName = AdminView.NAME)
public class AdminPresenter extends Presenter<AdminView> implements AdminListener {

	@Autowired
	EventBus.SessionEventBus eventBus;

	@Autowired
	EventBus.ApplicationEventBus applicationEventBus;

	@Autowired
	private LectureRestClient lectureClient;

	private Lecture currentLecture = new Lecture();

	@Override
	public Lecture getCurrentLecture() {

		return currentLecture;
	}

	@Override
	public void setCurrentLecture(Lecture lecture) {

		currentLecture = lecture;
	}

	@Override
	public void delete() {
		if (currentLecture != null)
			lectureClient.remove(currentLecture);
		else
			log.error("noCurrentLec");

	}

	@Override
	public int[] getVotes(Lecture lec) {

		log.info("{}", lectureClient.getPoll(lec.getOid()).getPoll());
		if (lectureClient.getPoll(lec.getOid()).getPoll() != null)
			return lectureClient.getPoll(lec.getOid()).getPoll();
		else
			return new int[2];
	}

	@Override
	public Lecture getLecture(Long oid) {
		log.info("{}" + oid);
		return lectureClient.getLectureByOID(oid);
	}

	@Override
	public void closePoll() {
		lectureClient.close(currentLecture.getOid());

	}

	@Override
	public void openPoll() {
		lectureClient.startNewPoll(currentLecture.getOid());

	}

	@Override
	public void askQuestion(String question) {
		lectureClient.askQuestion(currentLecture.getOid(), question);

	}

}
