package com.pilot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilot.dao.CustomerPricingEntity;
import com.pilot.dao.CustomerPricingRepository;

@RestController
@RequestMapping("/fuelController")
public class FuelContoller {
	
	@Autowired
	CustomerPricingRepository customerPricingRepository;
	
	// Get All Notes
	@GetMapping(path = "/customerPricing", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerPricingEntity> getAllNotes() {
	    return (List<CustomerPricingEntity>) customerPricingRepository.findAll();
	}

}


    