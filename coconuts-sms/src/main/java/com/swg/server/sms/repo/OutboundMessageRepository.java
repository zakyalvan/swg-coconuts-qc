package com.swg.server.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.swg.server.sms.entity.OutboundMessageBean;

public interface OutboundMessageRepository extends JpaRepository<OutboundMessageBean, Integer>, JpaSpecificationExecutor<OutboundMessageBean> {

}
