package com.pilot.main.herokurepo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilot.main.herokurepo.domain.PilotFuelIndustryStatsEntity;

@Repository
public interface  PilotFuelIndustryStatsRepo extends JpaRepository<PilotFuelIndustryStatsEntity, Long> {

}

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

