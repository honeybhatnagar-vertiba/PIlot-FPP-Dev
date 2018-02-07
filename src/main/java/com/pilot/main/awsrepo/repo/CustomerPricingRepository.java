package com.pilot.main.awsrepo.repo;

import org.springframework.data.repository.CrudRepository;

import com.pilot.main.awsrepo.domain.CustomerPricingEntity;



public interface  CustomerPricingRepository extends CrudRepository<CustomerPricingEntity, Long> {

}

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

