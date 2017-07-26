package de.fhdortmund.koopSys.DYUServer.ui.listener;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;

public interface AdminListener {

	public Lecture getCurrentLecture();

	public void setCurrentLecture(Lecture lecture);

	public void delete();

	public int[] getVotes(Lecture lec);

	public Lecture getLecture(Long oid);

	public void closePoll();

	public void openPoll();

	public void askQuestion(String value);

}
