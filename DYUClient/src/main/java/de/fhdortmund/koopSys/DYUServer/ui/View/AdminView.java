package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.fhdortmund.koopSys.DYUServer.ui.listener.AdminListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UIScope
@SpringView(name = AdminView.NAME)
public class AdminView extends VerticalLayout implements View {

	@Autowired
	AdminListener adminListener;

	private static final long serialVersionUID = 1L;
	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.AdminView";

	
	// Components
	private Button btnQuestion;
	private Button btnDeleteQuestion;
	private Button btnDelete;
	private TextField question;
			
	@PostConstruct
	private void _init() {
		setSizeFull();
		
		VerticalLayout verticalLayout = new VerticalLayout();
		
		// Textfield Username
				question = new TextField("Frage");
				question.setIcon(FontAwesome.QUESTION);
				question.focus();
				verticalLayout.addComponent(question);
				
		// Button		
				HorizontalLayout buttonLayout = new HorizontalLayout();
				btnQuestion = new Button("Frage stellen");
				btnDeleteQuestion = new Button("Frage schließen", FontAwesome.TIMES);
				buttonLayout.addComponent(btnQuestion);
				buttonLayout.addComponent(btnDeleteQuestion);
				verticalLayout.addComponent(buttonLayout);
				
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

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
