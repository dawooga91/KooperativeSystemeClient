package de.fhdortmund.koopSys.DYUServer.logic.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author droege_s
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Lecture extends BaseEntity {

	private String name;
	private int[] poll;
	private User admin;
	private List<User> users;
	private boolean open;
	private String question;
}
