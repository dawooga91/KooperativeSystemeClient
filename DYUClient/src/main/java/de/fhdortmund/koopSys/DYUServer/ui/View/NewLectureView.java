package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.ui.listener.NewLectureListener;
import de.fhdortmund.koopSys.DYUServer.ui.panel.LectureInputPanel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UIScope
@SpringView(name = NewLectureView.NAME)
public class NewLectureView extends Window implements View {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.NewLectureView";
	
	@Autowired
	private EventBus.SessionEventBus sessionBus;
	
	@Autowired
	NewLectureListener lectureListener;

	private LectureInputPanel lectureInputPanel;

	private Button btnCreateLecture;

	private Button btnCancel;

	@PostConstruct
	private void _init() {
		setCaption("Neue Vorlesung");
		center();
		setModal(true);
		setClosable(false);
		setResizable(false);

		lectureInputPanel = new LectureInputPanel();

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setMargin(new MarginInfo(false, true, true, true));
		buttonLayout.setSpacing(true);
		btnCreateLecture = new Button("Erstellen", FontAwesome.FLOPPY_O);
		buttonLayout.addComponent(btnCreateLecture);
		btnCancel = new Button("Abbrechen", FontAwesome.TIMES);
		buttonLayout.addComponent(btnCancel);
		addListener();

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.addComponent(lectureInputPanel);
		mainLayout.addComponent(buttonLayout);
		
		setContent(mainLayout);

	}

	private void addListener() {

		ClickListener buttonListener = getButtonListener();

		btnCancel.addClickListener(buttonListener);
		btnCreateLecture.addClickListener(buttonListener);

	}

	private ClickListener getButtonListener() {
		// TODO Auto-generated method stub
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

					Lecture newLecture = lectureInputPanel.getElement();
					lectureListener.createLecture(newLecture);
					
					sessionBus.publish(de.fhdortmund.koopSys.DYUServer.ui.Event.Event.CREATED_LECTURE,this, newLecture);
					
					close();
					
				} else if (pressedBtn == btnCancel)
					close();
			}
		};
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
