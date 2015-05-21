package com.swg.sms.entity.repo;

import com.swg.sms.entity.OutboundMessage;
import com.swg.sms.entity.OutboundMessageBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OutboundMessageRepository extends JpaRepository<OutboundMessageBean, Integer> {
    @Query(value = "SELECT COUNT(i) FROM OutboundMessageBean AS i WHERE i.status = 1")
    Long countNewMessages();

    List<OutboundMessage> findByStatus(int status);

    List<OutboundMessageBean> findByVersionGreaterThan(Date startVersion);

    List<OutboundMessageBean> findByVersionBetween(Date startVersion, Date endVersion);
}