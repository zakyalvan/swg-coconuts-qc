package com.swg.web.shared.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.swg.sms.entity.GatewayAudit;

@ProxyFor(value=GatewayAudit.class)
public interface GatewayAuditProxy extends ValueProxy {
	
}
