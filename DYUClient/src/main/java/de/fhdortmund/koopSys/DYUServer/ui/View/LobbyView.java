package de.fhdortmund.koopSys.DYUServer.ui.View;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
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

import de.fhdortmund.koopSys.DYUServer.logic.SessionManager;
import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LobbyListener;
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
	SessionManager sessionManager;

	// Components
	private Button btnJoin;

	private Button btnCreate;
	private List<Lecture> lectures;
	private Grid<Lecture> grid;

	private Navigator navigator;

	private Button btnRefresh;

	@Override
	public void enter(ViewChangeEvent event) {
		log.info("Refresh");
		navigator = UI.getCurrent().getNavigator();
		grid.setItems(lobbyListener.getLectureList());

	}

	@PostConstruct
	private void _init() {
		setCaption("Lobby");
		log.info("Lobby");

		setSizeFull();
		FormLayout lobbyForm = new FormLayout();

		HorizontalLayout liste = new HorizontalLayout();
		btnJoin = new Button("beitreten", VaadinIcons.CHEVRON_CIRCLE_RIGHT);// Button
		btnJoin.setClickShortcut(KeyCode.ENTER);
		btnJoin.addClickListener(getLobbyListener());

		btnCreate = new Button("Veranstaltung erstellen", VaadinIcons.PLUS);// Button
		btnCreate.addClickListener(getLobbyListener());

		btnRefresh = new Button("Aktualisieren", VaadinIcons.REFRESH);
		btnRefresh.addClickListener(getLobbyListener());

		grid = new Grid<>();
		lectures = lobbyListener.getLectureList();
		grid.setItems(lectures);
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.addColumn(Lecture::getName).setCaption("Vorlesung");

		liste.addComponent(grid);
		liste.addComponent(btnJoin);
		liste.addComponent(btnCreate);
		liste.addComponent(btnRefresh);

		// LobbyPanel Layout
		HorizontalLayout lobbyPanelLayout = new HorizontalLayout();
		lobbyPanelLayout.setMargin(true);
		lobbyPanelLayout.addComponent(lobbyForm);
		lobbyPanelLayout.addComponent(liste);
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
						lobbyListener.join(sessionManager.getIdentity(), lecture);
						navigator.navigateTo("LECTURE" + "/" + lecture.getOid());
					}
				}

				else if (button == btnCreate) {
					navigator.navigateTo("NEW_LECTURE");
				} else if (button == btnRefresh) {

					navigator.getCurrentView().enter(new ViewChangeEvent(navigator, navigator.getCurrentView(),
							navigator.getCurrentView(), "LOBBY", ""));
				}

			}

		};
	}

}
