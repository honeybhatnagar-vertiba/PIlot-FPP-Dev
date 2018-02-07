package com.pilot.main.herokurepo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pilot.main.herokurepo.domain.PilotFuelIndustryStatsEntity;

@Repository
public interface  PilotFuelIndustryStatsRepo extends CrudRepository<PilotFuelIndustryStatsEntity, Long> {

}

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

