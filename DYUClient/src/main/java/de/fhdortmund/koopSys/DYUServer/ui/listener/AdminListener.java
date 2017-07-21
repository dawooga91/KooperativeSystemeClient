package de.fhdortmund.koopSys.DYUServer.ui.listener;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;

public interface AdminListener {

	public Lecture getCurrentLecture();

	public void setCurrentLecture(Lecture lecture);

	public void delete();

	public int[] getVotes(Lecture lec);

}
