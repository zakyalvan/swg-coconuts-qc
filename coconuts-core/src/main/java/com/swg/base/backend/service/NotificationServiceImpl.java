package com.swg.base.backend.service;

import com.swg.base.backend.event.ApplicationNotificationEvent;
import com.swg.base.backend.repository.NotificationRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService, ApplicationListener<ApplicationNotificationEvent> {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired(required = true)
    private NotificationRepository notificationRepository;

    @Override
    public void onApplicationEvent(ApplicationNotificationEvent event) {
        logger.info("Event notification (" + event + ") baru diterima.");

    }
}
