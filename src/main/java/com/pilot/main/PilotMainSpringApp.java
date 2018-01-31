package com.pilot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;

@SpringBootApplication
public class PilotMainSpringApp {
	
	private static final Logger logger = LoggerFactory.getLogger(PilotMainSpringApp.class);
	
	public static void main(String[] args) {
		logger.info("starting spring");
		SpringApplication.run(PilotMainSpringApp.class, args);
	}
}
