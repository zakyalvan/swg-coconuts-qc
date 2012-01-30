package com.swg.web.shared.proxy;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.google.web.bindery.requestfactory.shared.EntityProxy;

public interface GatewayInfoProxy extends EntityProxy, Serializable {
	String getId();
	void setId(String id);
	Integer getCapability();
	void setCapability(Integer capability);
	boolean isEnabled();
	boolean isActivated();
	Set<GatewayAuditProxy> getAudits();
	Date getCreatedAt();
	Date getUpdatedAt();
	Date getVersion();
}