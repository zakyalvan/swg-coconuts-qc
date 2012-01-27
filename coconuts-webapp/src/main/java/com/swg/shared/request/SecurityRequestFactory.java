package com.swg.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.swg.core.service.SecurityService;
import com.swg.shared.ApplicationServiceLocator;

public interface SecurityRequestFactory extends RequestFactory {
	@Service(value=SecurityService.class, locator=ApplicationServiceLocator.class)
	public interface LoginInfoRequest extends RequestContext {
		public Request<Boolean> isLoggedIn();
	}
	
	public LoginInfoRequest getLoginInfoRequest();
}