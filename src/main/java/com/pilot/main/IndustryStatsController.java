package com.pilot.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilot.main.herokurepo.domain.PilotFuelIndustryStatsEntity;
import com.pilot.main.herokurepo.repo.PilotFuelIndustryStatsRepo;


@RestController
@CrossOrigin
@RequestMapping("/industryStats")
public class IndustryStatsController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndustryStatsController.class);
	
	@Autowired
	PilotFuelIndustryStatsRepo pilotFuelIndustryStatsRepo;
	
	// Get All Notes
	@GetMapping(path = "/details")
	public List<PilotFuelIndustryStatsEntity> getAllNotes() {
		logger.info("---in Fuel Controller ---");
	    return (List<PilotFuelIndustryStatsEntity>) pilotFuelIndustryStatsRepo.findAll();
	}
	
	
	
}