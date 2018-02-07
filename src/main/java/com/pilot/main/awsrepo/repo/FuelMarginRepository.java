package com.pilot.main.awsrepo.repo;

import org.springframework.data.repository.CrudRepository;

import com.pilot.main.awsrepo.domain.FuelMarginEntity;

public interface  FuelMarginRepository extends CrudRepository<FuelMarginEntity, Long> {

}