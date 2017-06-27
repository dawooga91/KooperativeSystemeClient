package de.fhdortmund.koopSys.DYUServer.logic;

import java.security.Identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;

import de.fhdortmund.koopSys.DYUServer.logic.entities.User;

/**
 * Vernatwortlich für die Sessionverwaltung. Verwaltet unter anderem den
 * Login-Status und bietet Zugriff auf den evtl. eingeloggten {@link Identity}.
 * Arbeitet auf der jeweiligen {@link VaadinSession}
 */
@SpringComponent
public class SessionManager {
	private static final Logger LOG = LoggerFactory.getLogger(SessionManager.class);

	/**
	 * Gibt die aktuelle Session
	 * 
	 * @return Aktuelle Session
	 */
	private VaadinSession getSession() {
		return VaadinSession.getCurrent();
	}

	/**
	 * @see VaadinSession#setAttribute(String, Object)
	 * @param name
	 * @param value
	 */
	public void setAttribute(SessionAttribute type, Object value) {
		getSession().setAttribute(type.getTextualRepresentation(), value);
	}

	/**
	 * @see VaadinSession#getAttribute(String)
	 * @param name
	 * @return
	 */
	public Object getAttribute(SessionAttribute type) {
		return getSession().getAttribute(type.getTextualRepresentation());
	}

	/**
	 * Gibt das Attribut mit dem Namen oder null, wenn es nicht von dem
	 * angegebenen Typ ist
	 * 
	 * @see #getAttribute(String)
	 * @param name
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String name, Class<T> type) {
		Object attribute = getSession().getAttribute(name);

		if (attribute != null && type.isAssignableFrom(attribute.getClass())) {
			return (T) attribute;
		}
		return null;
	}

	/**
	 * Assoziiert einen Benutzer mit dieser Session. Der Benutzer gilt danach
	 * als erfolgreich eingeloggt.<br>
	 * Der Benutzer kann vor einem {@link #logOut()} nicht mehr geändert werden.
	 * 
	 * @param identity
	 *            Der mit der Session verbundene {@link Identity}
	 */
	public void setIdentity(User user) {
		if (user == null) {
			throw new NullPointerException("Null value not allowed. For logout use function.");
		}
		if (isLoggedIn()) {
			LOG.warn("Try to set sessions user, but already logged in. New: '{}'", user);
			throw new RuntimeException("Changing user after login is not allowed - logout before new try");
		}
		LOG.info("Begin session for user '{}'", user);

		setAttribute(SessionAttribute.UserId, user);
	}

	/**
	 * Gibt den eingeloggten Benutzer, wenn eine erfolgreiche Loginprozedur
	 * durchgeführt wurde.<br>
	 * Ansonsten wird null zurückgegeben.
	 * 
	 * @return den assoziierten Benutzer oder null, wenn nicht vorhanden
	 */
	public User getIdentity() {
		User user = null;
		try {
			user = (User) getAttribute(SessionAttribute.UserId);
		} catch (Exception e) {
			logOut();
			LOG.error("Non identity found as session id -> {}", user);
		}
		return user;
	}

	/**
	 * Überprüft, ob ein gültiger (erfolgreich und nicht abgelaufen) Login mit
	 * der Session verknüpft ist.
	 * 
	 * @return true, wenn die Session einen erfolgreichen Login beinhaltet
	 */
	public boolean isLoggedIn() {
		boolean loggedIn = getIdentity() != null;
		return loggedIn;
	}

	/**
	 * Loggt den Benutzer aus und löscht die Session.
	 */
	public void logOut() {
		LOG.info("End session for user '{}'", getIdentity());
		setAttribute(SessionAttribute.UserId, null);
	}

}
