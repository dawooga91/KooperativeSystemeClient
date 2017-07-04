package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

/**
 * MainView wird bei Start abgerufen
 * 
 * @author droege_s
 *
 */
@UIScope
@SpringView(name = MainView.NAME)
public class MainView extends HorizontalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.MainView";

	@PostConstruct
	private void _init() {
		setSizeFull();
	}

	/**
	 * setzt eine neue View
	 * 
	 * @param c
	 *            - die zu setztende View
	 */
	public void setView(Component c) {
		removeAllComponents();
		addComponent(c);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}
}
