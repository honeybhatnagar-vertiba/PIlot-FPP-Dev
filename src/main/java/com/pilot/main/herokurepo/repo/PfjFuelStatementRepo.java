package com.pilot.main.herokurepo.repo;

import org.springframework.data.repository.CrudRepository;

import com.pilot.main.herokurepo.domain.PfjFuelStatementEntity;


public interface  PfjFuelStatementRepo extends CrudRepository<PfjFuelStatementEntity, Long> {

}

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

