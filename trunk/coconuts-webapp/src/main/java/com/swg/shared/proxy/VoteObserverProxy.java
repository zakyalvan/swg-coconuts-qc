package com.swg.shared.proxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.swg.core.entity.VoteObserver;
import com.swg.shared.proxy.locator.VoteObserverLocator;

/**
 * Client (gwt) object proxy untuk entity object {@link VoteObserver}.
 * Object ini gwt-related dan digunakan untuk keperluan fitur request factory.
 * 
 * @author zakyalvan
 *
 */
@ProxyFor(value=VoteObserver.class, locator=VoteObserverLocator.class)
public interface VoteObserverProxy extends EntityProxy {
	Integer getId();
	String getFullName();
	String getCellularNumber();
	Date getVersion();
}
