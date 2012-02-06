package com.swg.web.shared.proxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.swg.core.entity.VoteObserverBean;
import com.swg.web.server.entity.locator.VoteObserverLocator;

/**
 * Client (gwt) object proxy untuk entity object {@link VoteObserverBean}.
 * Object ini gwt-related dan digunakan untuk keperluan fitur request factory.
 * 
 * @author zakyalvan
 *
 */
@ProxyFor(value=VoteObserverBean.class, locator=VoteObserverLocator.class)
public interface VoteObserverProxy extends EntityProxy {
	Integer getId();
	
	String getFullName();
	void setFullName(String fullName);
	
	String getCellularNumber();
	void setCellularNumber(String cellularNumber);

	Date getRegisteredDate();
	void setRegisteredDate(Date registeredDate);

	boolean isVerified();
	void setVerified(boolean verified);

	Date getVerifiedDate();

	boolean isDeleted();
	void setDeleted(boolean deleted);
	
	Date getVersion();
}
