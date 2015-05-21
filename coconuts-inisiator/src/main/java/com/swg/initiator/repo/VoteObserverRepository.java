package com.swg.initiator.repo;

import com.swg.initiator.entity.VoteObserver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteObserverRepository extends JpaRepository<VoteObserver, Integer>, JpaSpecificationExecutor<VoteObserver> {
}
