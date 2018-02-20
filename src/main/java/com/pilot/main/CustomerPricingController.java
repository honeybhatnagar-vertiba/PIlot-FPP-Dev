package com.pilot.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilot.main.pilotrepo.entity.FctDmCompanyLevelActualVsTargetEntity;
import com.pilot.main.pilotrepo.repo.FctDmCompanyLevelActualVsTargetRepo;

@RestController
@CrossOrigin
@RequestMapping("/customerpricing")
public class CustomerPricingController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerPricingController.class);

	@Autowired
	FctDmCompanyLevelActualVsTargetRepo fctDmCompanyLevelActualVsTargetRepo;

	// Get All Notes
	@GetMapping(path = "/getprices")
	public List<FctDmCompanyLevelActualVsTargetEntity> getAllNotes() {
		logger.info("---in Customer Pricing Controller ---");
		return (List<FctDmCompanyLevelActualVsTargetEntity>) fctDmCompanyLevelActualVsTargetRepo.findAll();
	}
}