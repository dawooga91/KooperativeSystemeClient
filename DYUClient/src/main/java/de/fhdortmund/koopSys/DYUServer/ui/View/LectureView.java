package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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

	@PostConstruct
	private void _init() {
		
		setCaption(lectureListener.getLecture().getName());
		setSizeFull();
		
		setSizeFull();
		FormLayout pollForm = new FormLayout();
		
	
		lblQuestion = new Label("Warte auf Umfragen");
		
		
		
		pollForm.addComponent(lblQuestion);

		// Button
		HorizontalLayout footer = new HorizontalLayout();
		btnVoteYes = new Button("JA");
		btnVoteYes.addClickListener(getVoteListener());
		//btnVoteYes.setClickShortcut(KeyCode.ENTER);
		footer.addComponent(btnVoteYes);
		
		btnVoteNO = new Button("NEIN");
		footer.addComponent(btnVoteNO);
		

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
		

	}

	private ClickListener getVoteListener() {
		ClickListener clickListener= new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Button clickBtn = event.getButton();
				if(clickBtn==btnVoteYes){
					lectureListener.voteYes();
				}
				else if(clickBtn==btnVoteNO)
				{
					lectureListener.voteNo();
				}
				
			}
		};
		return clickListener;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
