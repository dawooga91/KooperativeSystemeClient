package de.fhdortmund.koopSys.DYUServer.ui.View;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.fhdortmund.koopSys.DYUServer.logic.entities.User;
import de.fhdortmund.koopSys.DYUServer.ui.listener.LoginListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Loginseite
 * 
 * @author droege_s
 *
 */
@Slf4j
@UIScope
@SpringView(name = LoginView.NAME)
public class LoginView extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "de.fhdortmund.koopSys.DYUServer.ui.View.LoginView";

	// Listener
	@Autowired
	private LoginListener loginListener;

	// Components
	private TextField tfUsername;
	private Button btnLogin;

	private Navigator navigator;

	@PostConstruct
	private void _init() {

		setSizeFull();
		FormLayout loginForm = new FormLayout();

		// Textfield Username
		tfUsername = new TextField("Username");
		tfUsername.setIcon(VaadinIcons.USER);
		tfUsername.focus();

		loginForm.addComponent(tfUsername);

		// Button
		HorizontalLayout footer = new HorizontalLayout();
		btnLogin = new Button("Login", VaadinIcons.CHECK);
		btnLogin.addClickListener(getLoginListener());
		btnLogin.setClickShortcut(KeyCode.ENTER);
		footer.addComponent(btnLogin);

		// LoginPanel Layout
		VerticalLayout loginPanelLayout = new VerticalLayout();
		loginPanelLayout.setMargin(true);
		loginPanelLayout.addComponent(loginForm);
		loginPanelLayout.addComponent(footer);

		// loginPanel
		Panel loginPanel = new Panel("Login");
		loginPanel.setWidthUndefined();
		loginPanel.setContent(loginPanelLayout);
		addComponent(loginPanel);
		setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

	}

	private ClickListener getLoginListener() {

		return new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.info("Loginbtn pressed");
				if (tfUsername.getValue().length() > 3) {
					User user = new User(tfUsername.getValue());
					loginListener.login(user);
					navigator.navigateTo("LOBBY");
				} else
					new Notification("Nutzername ungueltig", "Mindestens 4 Buchstaben",
							Notification.Type.WARNING_MESSAGE, true).show(Page.getCurrent());

			}
		};
	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = getUI().getNavigator();
	}

}
