package de.fhdortmund.koopSys.DYUServer.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import de.fhdortmund.koopSys.DYUServer.ui.presenter.MainPresenter;
import lombok.extern.slf4j.Slf4j;

/**
 * MainUi der Anwendung
 * 
 * @author droege_s
 *
 */
@Slf4j
@SpringUI(path = "/")
@Title("DYU-App")
@Theme("default")
public class MainUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MainPresenter mainPresenter;

	@Override
	protected void init(VaadinRequest request) {
		log.info("Starting UI");
		setContent(mainPresenter.getView());
		mainPresenter.start();
	}

}
