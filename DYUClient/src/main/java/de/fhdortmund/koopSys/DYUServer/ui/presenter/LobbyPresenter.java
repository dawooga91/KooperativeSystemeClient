package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.service.LectureRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.View.LobbyView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LobbyListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@VaadinPresenter(viewName = LobbyView.NAME)
public class LobbyPresenter extends Presenter<LobbyView> implements LobbyListener {

	
	
	@Autowired
	private EventBus.SessionEventBus eventBus;
	@Autowired
	private LectureRestClient lecClient;
	
	@Override
	public List<Lecture> getLectureList() {
		List<Lecture> allLecture = lecClient.getAllLecture();
		log.info("d%",allLecture.size());
		return allLecture;
	}

	
	

	
	

}
