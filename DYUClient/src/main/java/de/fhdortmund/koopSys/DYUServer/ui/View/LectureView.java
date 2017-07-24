package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.fhdortmund.koopSys.DYUServer.ui.listener.LectureListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UIScope
@SpringView(name = LectureView.NAME)
public class LectureView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.LectureView";

	@Autowired
	EventBus.SessionEventBus eventBus;

	@Autowired
	LectureListener lectureListener;

	private Button btnVoteYes;
	private Button btnVoteNO;
	private Label lblQuestion;
	private Navigator navigator;
	private Button btnRefresh;

	@PostConstruct
	private void _init() {

		// setCaption(lectureListener.getLecture().getName());
		setSizeFull();

		setSizeFull();
		FormLayout pollForm = new FormLayout();

		lblQuestion = new Label("Haben Sie das Verstanden");
		pollForm.addComponent(lblQuestion);

		// Button
		HorizontalLayout footer = new HorizontalLayout();
		btnVoteYes = new Button("JA", VaadinIcons.THUMBS_UP);
		btnVoteYes.addClickListener(getVoteListener());
		footer.addComponent(btnVoteYes);

		btnVoteNO = new Button("NEIN", VaadinIcons.THUMBS_DOWN);
		footer.addComponent(btnVoteNO);

		btnRefresh = new Button("Aktualisieren", VaadinIcons.REFRESH);
		btnRefresh.addClickListener(getVoteListener());
		footer.addComponent(btnRefresh);

		// LoginPanel Layout
		VerticalLayout pollPanelLayout = new VerticalLayout();
		pollPanelLayout.setMargin(true);
		pollPanelLayout.addComponent(pollForm);
		pollPanelLayout.addComponent(footer);

		// pollPanel
		Panel pollPanel = new Panel("Umfrage");
		pollPanel.setWidthUndefined();
		pollPanel.setContent(pollPanelLayout);
		addComponent(pollPanel);
		setComponentAlignment(pollPanel, Alignment.MIDDLE_CENTER);

		btnVoteNO.setDisableOnClick(true);
		btnVoteYes.setDisableOnClick(true);

	}

	private ClickListener getVoteListener() {
		ClickListener clickListener = new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (lectureListener.check()) {
					Button clickBtn = event.getButton();
					if (clickBtn == btnVoteYes) {
						lectureListener.voteYes();
						btnVoteNO.setEnabled(false);

					} else if (clickBtn == btnVoteNO) {
						lectureListener.voteNo();
						btnVoteYes.setEnabled(false);
					} else if (clickBtn == btnRefresh) {

						navigator.getCurrentView()
								.enter(new ViewChangeEvent(navigator, navigator.getCurrentView(),
										navigator.getCurrentView(), "ADMIN",
										Long.toString(lectureListener.getLecture().getOid())));
					}

				} else {
					new Notification("Lecture doesnt exist anymore", "Back to Lobby", Notification.Type.WARNING_MESSAGE,
							true).show(Page.getCurrent());
					navigator.navigateTo("LOBBY");
				}
			}
		};
		return clickListener;
	}

	// private void resetPoll() {
	// lblQuestion.setCaption("Haben sie das verstanden?");
	// btnVoteNO.setEnabled(true);
	// btnVoteYes.setEnabled(true);
	//
	// }

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = UI.getCurrent().getNavigator();
		if (event.getParameters() != null) {
			String parameters = event.getParameters();
			Long oid = Long.valueOf(parameters);
			lectureListener.setCurrentLecture(oid);

		}
		if (lectureListener.getLecture().isOpen()) {
			lblQuestion = new Label("Haben Sie das verstanden");
			btnVoteYes.setEnabled(true);
			btnVoteNO.setEnabled(true);
		} else {
			btnVoteNO.setEnabled(false);
			btnVoteYes.setEnabled(false);
		}
	}

}
