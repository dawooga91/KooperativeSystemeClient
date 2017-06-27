package de.fhdortmund.koopSys.DYUServer.ui.View;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView(name = LoginView.NAME)
public class LoginView extends VerticalLayout implements View {
	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.LoginView";

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
