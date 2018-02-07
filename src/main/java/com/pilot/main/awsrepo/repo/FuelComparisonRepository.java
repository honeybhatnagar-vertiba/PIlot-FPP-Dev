package com.pilot.main.awsrepo.repo;

import org.springframework.data.repository.CrudRepository;

import com.pilot.main.awsrepo.domain.FuelComparisonEntity;

public interface  FuelComparisonRepository extends CrudRepository<FuelComparisonEntity, Long> {

}