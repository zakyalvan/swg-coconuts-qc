package com.swg.base.backend.repository;

import com.swg.base.backend.entity.NotificationBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends
        JpaRepository<NotificationBean, Integer>,
        JpaSpecificationExecutor<NotificationBean> {

}
