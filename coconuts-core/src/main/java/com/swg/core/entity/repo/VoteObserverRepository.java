package com.swg.core.entity.repo;

import com.swg.core.entity.VoteObserverBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteObserverRepository extends JpaRepository<VoteObserverBean, Integer>, JpaSpecificationExecutor<VoteObserverBean> {
}
