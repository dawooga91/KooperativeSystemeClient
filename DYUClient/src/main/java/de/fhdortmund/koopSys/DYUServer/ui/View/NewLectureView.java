package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.ui.listener.NewLectureListener;
import de.fhdortmund.koopSys.DYUServer.ui.panel.LectureInputPanel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UIScope
@SpringView(name = NewLectureView.NAME)
public class NewLectureView extends VerticalLayout implements View {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.NewLectureView";

	@Autowired
	EventBus.SessionEventBus sessionBus;

	@Autowired
	EventBus.ApplicationEventBus applicationBus;

	@Autowired
	NewLectureListener lectureListener;

	private LectureInputPanel lectureInputPanel;

	private Button btnCreateLecture;

	private Button btnCancel;
	private Navigator navigator;

	@PostConstruct
	private void _init() {
		setCaption("Neue Vorlesung");

		lectureInputPanel = new LectureInputPanel();

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setMargin(new MarginInfo(false, true, true, true));
		buttonLayout.setSpacing(true);
		btnCreateLecture = new Button("Erstellen", VaadinIcons.PLUS);
		buttonLayout.addComponent(btnCreateLecture);
		btnCancel = new Button("Abbrechen", VaadinIcons.ARROW_CIRCLE_LEFT_O);
		buttonLayout.addComponent(btnCancel);
		addListener();

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.addComponent(lectureInputPanel);
		mainLayout.addComponent(buttonLayout);

		addComponent(mainLayout);

	}

	private void addListener() {

		ClickListener buttonListener = getButtonListener();

		btnCancel.addClickListener(buttonListener);
		btnCreateLecture.addClickListener(buttonListener);

	}

	private ClickListener getButtonListener() {
		return new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Button pressedBtn = event.getButton();
				if (pressedBtn == btnCreateLecture) {
					log.info("pressedCreatButton");
					if (lectureInputPanel.getElement().getName().length() > 4) {
						Lecture newLecture = lectureInputPanel.getElement();
						Lecture createLecture = lectureListener.createLecture(newLecture);
						navigator.navigateTo("ADMIN" + "/" + createLecture.getOid());
					} else
						new Notification("Name ungueltig", "Mindestens 4 Buchstaben", Notification.Type.WARNING_MESSAGE,
								true).show(Page.getCurrent());
				} else if (pressedBtn == btnCancel) {
					navigator.navigateTo("LOBBY");
				}
			}
		};

	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = getUI().getNavigator();

	}

}
