package com.swg.webapp.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.swg.core.service.SecurityService;
import com.swg.sms.service.MessageProcessingService;
import com.swg.web.server.ApplicationServiceLocator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:META-INF/spring/coconuts-config*.xml")
public class ServiceLocatorTest implements ApplicationContextAware {
	private Logger logger = Logger.getLogger(getClass());
	private ApplicationContext applicationContext;
	
	@Test
	public void testFindService() {
		logger.info("Test core service.");
		ApplicationServiceLocator serviceLocator = new ApplicationServiceLocator();
		
		SecurityService service = (SecurityService) serviceLocator.getInstance(SecurityService.class);
		logger.info("Class loaded : " + service.getClass().getName());
		
		//applicationContext.getBean(MessageProcessingService.class);
		MessageProcessingService processingService = (MessageProcessingService) serviceLocator.getInstance(MessageProcessingService.class);
		logger.info(processingService.getClass().getName());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
