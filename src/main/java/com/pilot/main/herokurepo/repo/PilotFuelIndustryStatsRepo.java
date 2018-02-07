package com.pilot.main.herokurepo.repo;

import org.springframework.data.repository.CrudRepository;

import com.pilot.main.herokurepo.domain.PilotFuelIndustryStatsEntity;


public interface  PilotFuelIndustryStatsRepo extends CrudRepository<PilotFuelIndustryStatsEntity, Long> {

}

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

