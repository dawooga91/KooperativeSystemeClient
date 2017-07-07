package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.fhdortmund.koopSys.DYUServer.ui.listener.CreateListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UIScope
@SpringView(name = LobbyView.NAME)
public class CreateView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.LobbyView";

	@Autowired
	private CreateListener createListener;

	@Autowired
	EventBus.SessionEventBus sessionBus;
	// Components
		private Button btnCreate;
		private Button btnCancel;
		private TextField LectureName;
		
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	@PostConstruct
	private void _init() {
		
		
		
		setSizeFull();
		FormLayout form = new FormLayout();
		
		
		// Textfield Veranstaltungsname
		LectureName = new TextField("Veranstaltung");
		LectureName.setIcon(FontAwesome.USER);
		LectureName.focus();
		new Binder<String>().forField(LectureName).withValidator(str -> str.length() <= 4, "At least 4 Characters");
		
		HorizontalLayout button = new HorizontalLayout();			
		btnCancel = new Button("Abbrechen");// Button
		btnCreate = new Button("Veranstaltung erstellen");// Button
		btnCreate.setClickShortcut(KeyCode.ENTER);
		//btnCreate.addClickListener(getCreaterListener());
		//btnCancel.addClickListener(getCreaterListener());	
		
		
		button.addComponent(btnCancel);
		button.addComponent(btnCreate);
		form.addComponent(LectureName);
		form.addComponent(button);
		
		
	}	

	private ClickListener getCreateListener() {

		return new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Button button = event.getButton();
				
				log.info("btn pressed");
				
				
				
				if(button == btnCancel)
				{
					sessionBus.publish(de.fhdortmund.koopSys.DYUServer.ui.Event.Event.CANCEL,this,"Cancel");
				}
				if(button == btnCreate)
				{
					sessionBus.publish(de.fhdortmund.koopSys.DYUServer.ui.Event.Event.CREATE_LECTURE, this, "Create");
				}
				
			}

			
		};
	}	
}