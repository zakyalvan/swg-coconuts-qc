package com.swg.web.shared.proxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.swg.core.entity.SiteReportBean;
import com.swg.web.server.entity.locator.SiteReportLocator;

@ProxyFor(value=SiteReportBean.class, locator=SiteReportLocator.class)
public interface SiteReportProxy extends EntityProxy {
	Integer getId();
	
	String getContent();
	
	Integer getPriority();
	void setPriority(Integer priority);
	
	Date getReportDate();
	
	Date getVersion();
}
