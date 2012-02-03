package com.swg.web.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.swg.server.sms.service.MessageService;
import com.swg.sms.entity.repo.InboundMessageRepository;
import com.swg.web.shared.ApplicationServiceLocator;

/**
 * Request factory untuk urusan manajemen pesan.
 * Kelas ini gwt-related, tepatnya fitur request-factory.
 * Jadi hanya dipake untuk keperluan front-end menggunakan gwt.
 * 
 * @author zakyalvan
 */
public interface MessagingRequestFactory extends RequestFactory {
	@Service(value=MessageService.class, locator=ApplicationServiceLocator.class)
	public interface ServiceLifecycleRequest extends RequestContext {
		public Request<Void> startService();
		public Request<Void> stopService();
	}
	
	@Service(value=InboundMessageRepository.class, locator=ApplicationServiceLocator.class)
	public interface InboundMessageRepositoryRequest extends RequestContext {
		
	}
	
	public ServiceLifecycleRequest getMessageServiceRequest();
	public InboundMessageRepositoryRequest getInboundMessageRepositoryRequest();
}
