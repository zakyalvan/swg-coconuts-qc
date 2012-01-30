package com.swg.web.shared.proxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.swg.server.sms.service.MessageService.MessageServiceStatus;

@ProxyFor(value=MessageServiceStatus.class)
public interface MessageServiceStatusProxy extends ValueProxy {
	public boolean isStarted();
	public Date getStartTime();
}
