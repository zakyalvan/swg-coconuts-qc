package com.swg.core.entity.repo;

import com.swg.core.entity.NotificationBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends
        JpaRepository<NotificationBean, Integer>,
        JpaSpecificationExecutor<NotificationBean> {

}
