package com.swg.core.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swg.core.entity.NotificationBean;

@Repository
public interface NotificationRepository extends
		JpaRepository<NotificationBean, Integer>,
		JpaSpecificationExecutor<NotificationBean> {

}
