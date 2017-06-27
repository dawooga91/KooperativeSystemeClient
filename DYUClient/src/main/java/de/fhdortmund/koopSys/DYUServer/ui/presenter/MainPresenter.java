package de.fhdortmund.koopSys.DYUServer.ui.presenter;

import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import de.fhdortmund.koopSys.DYUServer.logic.SessionManager;
import de.fhdortmund.koopSys.DYUServer.ui.View.MainView;

/**
 * Presenter für den MainView
 * 
 * @author droege_s
 *
 */
@VaadinPresenter(viewName = MainView.NAME)
public class MainPresenter extends Presenter<MainView> {

	private SessionManager sessionManager;
	private LoginPresenter loginPresenter;

	/**
	 * Wird zum starten ausgeführt
	 */
	public void start() {

	}

}
