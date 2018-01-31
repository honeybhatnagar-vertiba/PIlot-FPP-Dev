package com.pilot.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("*/fuelController")
public class FuelContoller {
	
	@Autowired
	CustomerPricingRepository customerPricingRepository;
	
	// Get All Notes
	@GetMapping(path = "/customerPricing")
	public List<CustomerPricingEntity> getAllNotes() {
	    return (List<CustomerPricingEntity>) customerPricingRepository.findAll();
	}

}


    