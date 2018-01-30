package com.example.pilotpoc;

import org.springframework.data.repository.CrudRepository;



public interface  CustomerPricingRepository extends CrudRepository<CustomerPricingEntity, Long> {

}

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

