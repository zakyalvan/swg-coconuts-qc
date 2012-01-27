package com.swg.sms.entity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swg.sms.entity.InboundMessage;
import com.swg.sms.entity.InboundMessageBean;

@Repository
public interface InboundMessageRepository extends JpaRepository<InboundMessageBean, Integer> {
	@Query(value="SELECT COUNT(i) FROM InboundMessageBean AS i WHERE i.status = 1")
	Long countNewMessages();
	
	List<InboundMessage> findByStatus(Integer status);
	
	@Query(value="SELECT i FROM InboundMessageBean AS i WHERE i.status IN :statuses")
	List<InboundMessage> findByStatusIn(@Param("statuses") List<Integer> statuses);
}
