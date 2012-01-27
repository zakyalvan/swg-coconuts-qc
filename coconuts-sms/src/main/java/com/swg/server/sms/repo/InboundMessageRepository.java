package com.swg.server.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swg.server.sms.entity.InboundMessageBean;

@Repository
public interface InboundMessageRepository extends JpaRepository<InboundMessageBean, Integer>, JpaSpecificationExecutor<InboundMessageBean> {}