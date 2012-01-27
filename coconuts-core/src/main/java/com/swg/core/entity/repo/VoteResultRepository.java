package com.swg.core.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swg.core.entity.VoteResult;

/**
 * Repository data hasil perhitungan perolehan suara.
 * 
 * @author zakyalvan
 */
@Repository
public interface VoteResultRepository extends
		JpaRepository<VoteResult, Integer>,
		JpaSpecificationExecutor<VoteResult> {

}
