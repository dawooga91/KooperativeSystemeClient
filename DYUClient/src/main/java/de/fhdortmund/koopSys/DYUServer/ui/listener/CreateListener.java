package de.fhdortmund.koopSys.DYUServer.ui.listener;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;

/**
 * Listener für das erstellen von Vorlesungen
 * 
 * @author droege_s
 *
 */
public interface CreateListener {

	public void creatLecture(Lecture lecture);
}
