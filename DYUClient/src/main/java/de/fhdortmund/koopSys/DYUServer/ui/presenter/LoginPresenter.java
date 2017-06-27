package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.navigator.Presenter;

import de.fhdortmund.koopSys.DYUServer.service.LoginRestClient;
import de.fhdortmund.koopSys.DYUServer.ui.View.LoginView;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LoginListener;

/**
 * Presenter für den Login
 * 
 * @author droege_s
 *
 */
public class LoginPresenter extends Presenter<LoginView> implements LoginListener {

	@Autowired
	LoginRestClient loginClient;

	@Override
	public void login(String name) {
		// TODO Auto-generated method stub

	}

}
