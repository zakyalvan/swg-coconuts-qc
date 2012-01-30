package com.swg.web.shared;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * Ini bean yang nge-locate service/repository bean yang di proxy-in di client 
 * Fitur dipake untuk komponen gwt request factory.
 * 
 * @author zakyalvan
 */
@Component
@Configurable
@Scope(value="prototype")
public class ApplicationServiceLocator implements ApplicationContextAware, ServiceLocator {
	private Logger logger = Logger.getLogger(getClass());
	private ApplicationContext applicationContext;
	
	@Override
	public Object getInstance(Class<?> clazz) {
		logger.debug("Get bean reference of type " + clazz + " from spring container.");
		return applicationContext.getBean(clazz);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.debug("Application context for object " + this + " injected.");
		this.applicationContext = applicationContext;
	}
}