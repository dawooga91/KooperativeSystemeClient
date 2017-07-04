package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.ui.View.LobbyView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LobbyListener;

@VaadinPresenter(viewName = LobbyView.NAME)
public class LobbyPresenter extends Presenter<LobbyView> implements LobbyListener {

	@Autowired
	private EventBus.SessionEventBus eventBus;

}
