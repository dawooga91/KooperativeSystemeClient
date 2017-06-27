package de.fhdortmund.koopSys.DYUServer.logic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Basis-Entity, die den Primärschlüssel enthält.
 * 
 *
 * @author droege_s
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

	private long oid;

}
