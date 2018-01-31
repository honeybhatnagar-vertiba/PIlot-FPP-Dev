package com.pilot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PilotMainSpringApp {
	
	private static final Logger logger = LoggerFactory.getLogger(PilotMainSpringApp.class);
	
	@Value("${localDatabaseUrl}")
    private static String localDatabaseUrl;

	public static void main(String[] args) {
		
		logger.info("----localDatabaseUrl-----"+localDatabaseUrl);
		SpringApplication.run(PilotMainSpringApp.class, args);
	}
}
