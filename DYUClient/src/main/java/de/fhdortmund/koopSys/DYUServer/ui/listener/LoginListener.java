package de.fhdortmund.koopSys.DYUServer.ui.listener;

import de.fhdortmund.koopSys.DYUServer.logic.entities.User;

/**
 * Listener für den Login
 * 
 * @author droege_s
 *
 */
public interface LoginListener {

	public void login(User user);
}
