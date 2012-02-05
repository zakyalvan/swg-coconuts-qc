package com.swg.web.shared.proxy;

import java.io.Serializable;
import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.swg.sms.entity.InboundMessageBean;
import com.swg.web.server.entity.locator.InboundMessageLocator;

@ProxyFor(value=InboundMessageBean.class, locator=InboundMessageLocator.class)
public interface InboundMessageProxy extends EntityProxy, Serializable {
	Integer getId();
	void setId(Integer id);
	
	String getContent();
	void setContent(String content);
	
	String getSender();
	void setSender(String sender);
	
	String getServiceCenter();
	void setServiceCenter(String serviceCenter);
	
	Date getReceiveDate();
	void setReceiveDate(Date receiveData);
	
	Integer getStatus();
	void setStatus(Integer status);
	
	Date getVersion();
	public void setVersion(Date version);
}
