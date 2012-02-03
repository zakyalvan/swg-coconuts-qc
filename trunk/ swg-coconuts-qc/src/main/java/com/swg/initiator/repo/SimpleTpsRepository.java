package com.swg.initiator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.swg.initiator.entity.SimpleTps;

public interface SimpleTpsRepository extends JpaRepository<SimpleTps, Integer>, JpaSpecificationExecutor<SimpleTps>{

}
