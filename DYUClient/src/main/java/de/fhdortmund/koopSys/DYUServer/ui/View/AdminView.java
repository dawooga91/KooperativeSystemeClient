package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.apache.commons.math.ode.nonstiff.AdamsMoultonIntegrator;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.components.grid.SingleSelectionModel;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.ui.listener.AdminListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UIScope
@SpringView(name = AdminView.NAME)
public class AdminView extends VerticalLayout implements View {

	@Autowired
	AdminListener adminListener;
	
	@Autowired
	EventBus.SessionEventBus sessionBus;

	private static final long serialVersionUID = 1L;
	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.AdminView";

	
	// Components
	private Button btnQuestion;
	private Button btnDeleteQuestion;
	private Button btnDelete;
	private TextField question;
	private Label questionLabel;
	private Label userCountLabel;
	private Label yesLabel;
	private Label noLabel;
	private Label prozentLabel;
	
	private String Frage = "Noch keine Frage gestellt!";
	private int userCount = 0;
	private int yes  = 0 ;
	private int no = 0;
	private int prozent  = 0;
			
	@PostConstruct
	private void _init() {
		setSizeFull();
		
		VerticalLayout verticalLayout = new VerticalLayout();
		
		// Textfield Frage
				question = new TextField("Frage");
				question.setIcon(FontAwesome.QUESTION);
				question.focus();
				verticalLayout.addComponent(question);
				
		// Button	Frage stellen & Frage schließen
				HorizontalLayout buttonLayout = new HorizontalLayout();
				btnQuestion = new Button("Frage stellen");
				btnDeleteQuestion = new Button("Frage schließen", FontAwesome.TIMES);
				buttonLayout.addComponent(btnQuestion);
				buttonLayout.addComponent(btnDeleteQuestion);
				verticalLayout.addComponent(buttonLayout);
				
		//Anzeige
				VerticalLayout lectureLayout = new VerticalLayout();
				questionLabel = new Label(Frage);
				
				HorizontalLayout thumps = new HorizontalLayout();
				yesLabel = new Label("" + yes);
				yesLabel.setIcon(FontAwesome.THUMBS_UP);
				noLabel = new Label("" + no);
				noLabel.setIcon(FontAwesome.THUMBS_DOWN);
				userCountLabel = new Label("" + userCount);
				userCountLabel.setIcon(FontAwesome.USERS);
				thumps.addComponent(yesLabel);
				thumps.addComponent(noLabel);
				thumps.addComponent(userCountLabel);
				
		//Prozentangabe
				if(userCount==0  || (yes==0 && no==0)) {
					prozentLabel = new Label("Es hat noch niemand an der Abstimmung teilgenommen!");
					}
				else {
					prozent = yes/userCount*100;
					prozentLabel = new Label("Es haben " + prozent +"% mit JA abgestimmt!");
				}
				
				lectureLayout.addComponent(questionLabel);		
				lectureLayout.addComponent(thumps);
				lectureLayout.addComponent(prozentLabel);
				
				verticalLayout.addComponent(lectureLayout);
				
		// Button	Vorlesung löschen	
				btnDelete = new Button("Vorlesung löschen", FontAwesome.TRASH);	
				verticalLayout.addComponent(btnDelete);
				
				
		// lobbyPanel
				Panel lobbyPanel = new Panel("Name der Vorlesung");
				lobbyPanel.setWidthUndefined();
				lobbyPanel.setContent(verticalLayout);
				addComponent(lobbyPanel);
				setComponentAlignment(lobbyPanel, Alignment.MIDDLE_CENTER);
				
		//setCaption("");
		
		
		

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
				Button button = event.getButton();
												
				if(button == btnQuestion)
				{
					log.info("btnQuestion pressed");
					sessionBus.publish(de.fhdortmund.koopSys.DYUServer.ui.Event.Event.NEW_QUESTION, this, "Hallo");
					
				}
				if(button == btnDeleteQuestion)
				{
					log.info("btnDeleteQuestion pressed");
					sessionBus.publish(de.fhdortmund.koopSys.DYUServer.ui.Event.Event.DELETE_QUESTION, this, "Hallo");
				}
				
				if(button == btnDelete)
				{
					log.info("btnDelete pressed");
					sessionBus.publish(de.fhdortmund.koopSys.DYUServer.ui.Event.Event.DELETE_LECTURE, this, adminListener.get);
				}
				
			}
};
}


@Override
public void enter(ViewChangeEvent event) {
	// TODO Auto-generated method stub
	
}

}