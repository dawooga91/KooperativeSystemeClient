package de.fhdortmund.koopSys.DYUServer.ui.Event;

/**
 * Events, für den Eventbus
 * 
 * @author droege_s
 *
 */
public abstract class Event {

	// Session
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "LOGOUT";

	// Vorlesung
	public static final String CREATE_LECTURE = "CREATE_LECTURE";
	public static final String NEW_LECTURE = "NEW_LECTURE";
	public static final String JOIN = "JOIN";
	public static final String CANCEL = "CANCEL";
	public static final String CREATED_LECTURE = "CREATED_LECTURE";
	
	//Admin
	public static final String DELETE_LECTURE = "DELETE_LECTURE";
	public static final String DELETE_QUESTION = "DELETE_QUESTION";
	public static final String NEW_QUESTION = "NEW_QUESTION";
}
