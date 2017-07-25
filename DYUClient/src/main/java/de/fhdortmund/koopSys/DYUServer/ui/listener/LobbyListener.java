package de.fhdortmund.koopSys.DYUServer.ui.listener;

import java.util.List;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;
import de.fhdortmund.koopSys.DYUServer.logic.entities.User;

public interface LobbyListener {

	public List<Lecture> getLectureList();

	public void join(User identity, Lecture lecture);

}
