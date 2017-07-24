package de.fhdortmund.koopSys.DYUServer.ui.View;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.events.annotation.EventBusListenerTopic;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.SingleSelectionModel;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LobbyListener;
import de.fhdortmund.koopSys.DYUServer.ui.presenter.NewLecturePresenter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UIScope
@SpringView(name = LobbyView.NAME)
public class LobbyView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.LobbyView";

	@Autowired
	private LobbyListener lobbyListener;

	@Autowired
	EventBus.SessionEventBus sessionBus;
	
	
	@Autowired
	private NewLecturePresenter newLecturePresenter;
	// Components
	private Button btnJoin;

	private Button btnCreate;
	private List<Lecture> lectures;
	private Grid<Lecture> grid;

	private Navigator navigator;

	@Override
	public void enter(ViewChangeEvent event) {
		
		navigator = UI.getCurrent().getNavigator();
		if (event.getParameters() != null) {
			grid.setItems(lobbyListener.getLectureList());
		}

	}

	@PostConstruct
	private void _init() {
		setCaption("Lobby");
		log.info("Lobby");

		setSizeFull();
		FormLayout lobbyForm = new FormLayout();

		HorizontalLayout Liste = new HorizontalLayout();
		btnJoin = new Button("beitreten");// Button
		btnCreate = new Button("Veranstaltung erstellen");// Button
		btnJoin.setClickShortcut(KeyCode.ENTER);
		btnJoin.addClickListener(getLobbyListener());
		btnCreate.addClickListener(getLobbyListener());
		grid = new Grid<>();
		lectures = lobbyListener.getLectureList();
		grid.setItems(lectures);
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.addColumn(Lecture::getName).setCaption("Vorlesung");

		Liste.addComponent(grid);
		Liste.addComponent(btnJoin);
		Liste.addComponent(btnCreate);

		// LobbyPanel Layout
		HorizontalLayout lobbyPanelLayout = new HorizontalLayout();
		lobbyPanelLayout.setMargin(true);
		lobbyPanelLayout.addComponent(lobbyForm);
		lobbyPanelLayout.addComponent(Liste);
		SingleSelectionModel<Lecture> singleSelect = (SingleSelectionModel<Lecture>) grid.getSelectionModel();

		// loginPanel
		Panel lobbyPanel = new Panel("Lobby");
		lobbyPanel.setWidthUndefined();
		lobbyPanel.setContent(lobbyPanelLayout);
		addComponent(lobbyPanel);
		setComponentAlignment(lobbyPanel, Alignment.MIDDLE_CENTER);
	}
	
	
	@PostConstruct
	private ClickListener getLobbyListener() {

		return new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.info("Joinbtn pressed");
				Button button = event.getButton();

				log.info("btn pressed");

				if (button == btnJoin) {
					Set<Lecture> selectedItems = grid.getSelectedItems();
					for (Lecture lecture : selectedItems) {
						navigator.navigateTo("Lecture"+lecture.getOid());
					}
				}

				if (button == btnCreate) {
					navigator.navigateTo("NEW_LECTURE");
				}

			}

		};
	}

}
