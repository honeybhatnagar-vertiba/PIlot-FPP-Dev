package com.pilot.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilot.main.pilotservice.pojo.CustomerPricingDetail;
import com.pilot.main.pilotservice.service.CustomerPricingService;

@RestController
@CrossOrigin
@RequestMapping("/customerpricing")
public class CustomerPricingController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerPricingController.class);

	@Autowired
	CustomerPricingService customerPricingService;

	// Get All Notes
	@GetMapping(path = "/getprices")
	public List<CustomerPricingDetail> getAllNotes() {
		logger.info("---in Customer Pricing Controller ---");
		return customerPricingService.fetchCustomerPricingDetails();
	}
}