package com.pilot.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fuelController")
public class FuelContoller {
	
	private static final Logger logger = LoggerFactory.getLogger(PilotMainSpringApp.class);
	
	@Autowired
	CustomerPricingRepository customerPricingRepository;
	
	// Get All Notes
	@GetMapping(path = "/customerPricing")
	public List<CustomerPricingEntity> getAllNotes() {
		logger.info("---in Fuel Controller ---");
	    return (List<CustomerPricingEntity>) customerPricingRepository.findAll();
	}
	
	
	
}


    