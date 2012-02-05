package com.swg.web.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.swg.sms.entity.repo.InboundMessageRepository;
import com.swg.sms.service.MessageProcessingService;
import com.swg.sms.service.ServiceLifecycleManager;
import com.swg.web.server.ApplicationServiceLocator;
import com.swg.web.shared.proxy.InboundMessageProxy;
import com.swg.web.shared.proxy.OutboundMessageProxy;

/**
 * Request factory untuk urusan manajemen pesan.
 * Kelas ini gwt-related, tepatnya fitur request-factory.
 * Jadi hanya dipake untuk keperluan front-end menggunakan gwt.
 * 
 * @author zakyalvan
 */
public interface MessagingRequestFactory extends RequestFactory {
	@Service(value=ServiceLifecycleManager.class, locator=ApplicationServiceLocator.class)
	public interface ServiceLifecycleRequest extends RequestContext {
		Request<Void> startService();
		Request<Void> stopService();
	}
	
	@Service(value=MessageProcessingService.class, locator=ApplicationServiceLocator.class)
	public interface MessageProcessingRequest extends RequestContext {
		Request<Void> reprocessMessage(InboundMessageProxy inboundMessage);
		Request<Void> processMessage(OutboundMessageProxy outboundMessage, boolean enforceAlreadyProcessedMessage);
		Request<Void> processMessage(OutboundMessageProxy outboundMessage);
	}
	
	@Service(value=InboundMessageRepository.class, locator=ApplicationServiceLocator.class)
	public interface InboundMessageRepositoryRequest extends RequestContext {
		
	}
	
	public ServiceLifecycleRequest getServiceLifecycleRequest();
	public MessageProcessingRequest getMessageProcessingRequest();
	public InboundMessageRepositoryRequest getInboundMessageRepositoryRequest();
}
