package com.swg.core.service;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.swg.core.event.ApplicationNotificationEvent;

@Service
public class NotificationServiceImpl implements NotificationService, ApplicationListener<ApplicationNotificationEvent> {
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void onApplicationEvent(ApplicationNotificationEvent event) {
		logger.info("Event notification ("+event+") baru diterima.");
		
	}
}
