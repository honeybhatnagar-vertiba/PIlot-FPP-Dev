package com.example.pilotpoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
	@Value("${localDatabaseUrl}")
    private static String localDatabaseUrl;

	public static void main(String[] args) {
		
		logger.info("----localDatabaseUrl-----"+localDatabaseUrl);
		SpringApplication.run(DemoApplication.class, args);
	}
}
