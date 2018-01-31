package com.pilot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PilotMainSpringApp {
	
	private static final Logger logger = LoggerFactory.getLogger(PilotMainSpringApp.class);
	
	public static void main(String[] args) {
		logger.info("starting spring");
		SpringApplication.run(PilotMainSpringApp.class, args);
	}
}
