package de.fhdortmund.koopSys.DYUServer.ui.listener;

import de.fhdortmund.koopSys.DYUServer.logic.entities.User;

public interface UserListener {

	public void logoutUser(User user);

	public void loginUser(User user);
}
