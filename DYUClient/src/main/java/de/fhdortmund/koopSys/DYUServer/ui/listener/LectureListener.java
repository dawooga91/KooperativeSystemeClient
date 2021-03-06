package de.fhdortmund.koopSys.DYUServer.ui.listener;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;

/**
 * Listener für die Vorlesungen
 * 
 * @author droege_s
 *
 */
public interface LectureListener {

	public void createNewLecture(Lecture lecture);

	public Lecture getLecture();

	public void voteYes();

	public void voteNo();

	public boolean check();

	public void setCurrentLecture(Long oid);

	public String getQuestion();

}
