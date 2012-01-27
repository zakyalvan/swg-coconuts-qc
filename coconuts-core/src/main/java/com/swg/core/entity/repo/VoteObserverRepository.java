package com.swg.core.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swg.core.entity.VoteObserver;

@Repository
public interface VoteObserverRepository extends JpaRepository<VoteObserver, Integer>, JpaSpecificationExecutor<VoteObserver> {
}
