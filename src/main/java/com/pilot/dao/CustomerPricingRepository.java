package com.pilot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface  CustomerPricingRepository extends CrudRepository<CustomerPricingEntity, Long> {

}

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

