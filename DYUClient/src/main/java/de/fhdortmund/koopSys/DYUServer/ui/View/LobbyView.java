package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

import de.fhdortmund.koopSys.DYUServer.ui.listener.LobbyListener;

@UIScope
@SpringView(name = LobbyView.NAME)
public class LobbyView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.LobbyView";

	@Autowired
	private LobbyListener lobbyListener;

	@PostConstruct
	private void _init() {

		setSizeFull();
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
