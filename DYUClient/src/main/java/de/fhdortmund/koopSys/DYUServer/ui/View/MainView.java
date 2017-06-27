package de.fhdortmund.koopSys.DYUServer.ui.View;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
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
	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.MainView";

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
