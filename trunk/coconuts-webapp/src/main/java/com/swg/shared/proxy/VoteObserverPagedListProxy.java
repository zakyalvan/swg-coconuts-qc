package com.swg.shared.proxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.swg.core.service.BasePagedList;
import com.swg.core.service.VoteObserverPagedList;

/**
 * Proxy object untuk {@link BasePagedList} untuk client berbasis gwt.
 * Interface ini gwt-related.
 * 
 * @author zakyalvan
 *
 * @param <T>
 */
@ProxyFor(value=VoteObserverPagedList.class)
public interface VoteObserverPagedListProxy extends ValueProxy {
	Integer getPage();
	Integer getSize();
	Integer getOffset();
	
	Long getTotal();
	
	List<VoteObserverProxy> getDatas();
}