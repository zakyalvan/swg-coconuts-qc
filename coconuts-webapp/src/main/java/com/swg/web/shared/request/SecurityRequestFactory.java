package com.swg.web.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.swg.core.service.SecurityService;
import com.swg.web.server.ApplicationServiceLocator;

public interface SecurityRequestFactory extends RequestFactory {
	@Service(value=SecurityService.class, locator=ApplicationServiceLocator.class)
	public interface LoginInfoRequest extends RequestContext {
		public Request<Boolean> isLoggedIn();
	}
	
	public LoginInfoRequest getLoginInfoRequest();
}