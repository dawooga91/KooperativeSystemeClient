package de.fhdortmund.koopSys.DYUServer.service;

import lombok.extern.slf4j.Slf4j;

/**
 * Konfiguration für die ReST-Clients
 * 
 * @author droege
 *
 */
@Slf4j
public class RestClientConfiguration {

	/**
	 * Pfad zum Server, der den ReST-Service anbietet.
	 */
	public static final String BASE_PATH = "http://localhost:8090/api";

	static {
		log.info("Basepath => {}", BASE_PATH);
	}

}
