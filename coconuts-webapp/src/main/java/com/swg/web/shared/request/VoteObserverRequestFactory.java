package com.swg.web.shared.request;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.swg.core.service.VoteObserverManager;
import com.swg.web.shared.ApplicationServiceLocator;
import com.swg.web.shared.proxy.VoteObserverPagedListProxy;
import com.swg.web.shared.proxy.VoteObserverProxy;

/**
 * Request factory yang berkaitan dengan pemantau atau saksi pemungutan suara.
 * 
 * @author zakyalvan
 *
 */
public interface VoteObserverRequestFactory extends RequestFactory {
	@Service(value=VoteObserverManager.class, locator=ApplicationServiceLocator.class)
	public interface ManagementRequest extends RequestContext {
		Request<Void> delete(VoteObserverProxy proxy);
		Request<Void> delete(List<VoteObserverProxy> proxies);
		
		Request<Long> countVoteObservers();
		Request<List<VoteObserverProxy>> listVoteObservers();
		Request<VoteObserverPagedListProxy> listVoteObservers(Integer offset, Integer limit);
	}
	
	public ManagementRequest getManagementRequest();
}
