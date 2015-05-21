package com.swg.initiator.repo;

import com.swg.initiator.entity.SimpleTps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SimpleTpsRepository extends JpaRepository<SimpleTps, Integer>, JpaSpecificationExecutor<SimpleTps> {

}
