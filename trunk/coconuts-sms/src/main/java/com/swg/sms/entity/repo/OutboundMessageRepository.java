package com.swg.sms.entity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swg.sms.entity.OutboundMessage;
import com.swg.sms.entity.OutboundMessageBean;

@Repository
public interface OutboundMessageRepository extends JpaRepository<OutboundMessageBean, Integer> {
	@Query(value="SELECT COUNT(i) FROM OutboundMessageBean AS i WHERE i.status = 1")
	Long countNewMessages();
	
	List<OutboundMessage> findAllByStatus(int status);
}