package de.fhdortmund.koopSys.DYUServer.logic;

import javax.servlet.ServletRequest;

import de.fhdortmund.koopSys.DYUServer.util.Null;

/**
 * Attribute, die als Key für {@link HttpSession#setAttribute(String, Object)}
 * dienen
 */
/**
 * @author droege_s
 *
 */
public enum SessionAttribute {
	/**
	 * Benutzer ID des in die Session eingeloggten Users
	 */
	UserId;

	private String textualRepresentation;

	private SessionAttribute(String textualRepresentation) {
		this.textualRepresentation = textualRepresentation;
	}

	private SessionAttribute() {
		this(null);
	}

	/**
	 * Gibt eine textuelle Repräsentation des Enums
	 * 
	 * @return Den Namen des Enums wie durch {@link #name()} oder die textuelle
	 *         Repräsentation wie im Constructor
	 *         {@link #SessionAttribute(String)} angegeben
	 */
	public String getTextualRepresentation() {
		return Null.nvl(this.textualRepresentation, this.name());
	}

	/**
	 * String Darstellung des Attributes. Kann für
	 * {@link ServletRequest#setAttribute(String, Object)} als Key verwendet
	 * werden
	 * 
	 * @return String Darstellung des Attributes
	 */
	public String toString() {
		return getTextualRepresentation();
	}
}