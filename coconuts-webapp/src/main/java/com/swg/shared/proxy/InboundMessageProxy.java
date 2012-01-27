package com.swg.shared.proxy;

import java.io.Serializable;
import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.swg.server.sms.entity.InboundMessageBean;
import com.swg.shared.proxy.locator.InboundMessageLocator;

@ProxyFor(value=InboundMessageBean.class, locator=InboundMessageLocator.class)
public interface InboundMessageProxy extends EntityProxy, Serializable {
	Integer getId();
	String getContent();
	String getSender();
	String getServiceCenter();
	Date getReceiveDate();
	Date getVersion();
}
