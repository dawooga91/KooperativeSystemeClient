package de.fhdortmund.koopSys.DYUServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.spring.events.annotation.EnableEventBus;

@EnableEventBus
@SpringBootApplication
public class DyuServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DyuServerApplication.class, args);
	}

}
