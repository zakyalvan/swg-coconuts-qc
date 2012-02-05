package com.swg.web.shared.proxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.swg.sms.entity.OutboundMessageBean;
import com.swg.web.server.entity.locator.OutboundMessageLocator;

@ProxyFor(value=OutboundMessageBean.class, locator=OutboundMessageLocator.class)
public interface OutboundMessageProxy extends EntityProxy {
	Integer getId();
	
	String getRecipient();
	void setRecipient(String recipient);
	
	String getContent();
	void setContent(String content);
	
	int getStatus();
	void setStatus(int status);
	
	Date getCreateDate();
	
	Date getSentDate();
	void setSentDate(Date sentDate);
	
	Date getVersion();
}
