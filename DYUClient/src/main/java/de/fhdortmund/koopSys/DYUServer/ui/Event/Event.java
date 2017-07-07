package de.fhdortmund.koopSys.DYUServer.ui.Event;

import org.vaadin.spring.events.EventScope;

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
	
}
