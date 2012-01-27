package com.swg.shared.request;

import java.util.Set;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.swg.server.sms.repo.InboundMessageRepository;
import com.swg.server.sms.service.MessageService;
import com.swg.server.sms.service.SerialGatewayInfoManager;
import com.swg.shared.ApplicationServiceLocator;
import com.swg.shared.proxy.SerialGatewayInfoProxy;

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
//		public Request<MessageServiceStatusProxy> getStatus();
	}
	
	@Service(value=SerialGatewayInfoManager.class, locator=ApplicationServiceLocator.class)
	public interface SerialGatewayManagementRequest extends RequestContext {
		Request<Void> registerGateway(SerialGatewayInfoProxy serialGateway);
		Request<SerialGatewayInfoProxy> getGateway(String id);
		Request<Set<SerialGatewayInfoProxy>> getGateways();
		Request<Set<SerialGatewayInfoProxy>> getGateways(Integer capability);
		Request<SerialGatewayInfoProxy> enableGateway(String id);
		Request<SerialGatewayInfoProxy> disableGateway(String id);
		Request<SerialGatewayInfoProxy> removeGateway(String id);
	}
	
	@Service(value=InboundMessageRepository.class, locator=ApplicationServiceLocator.class)
	public interface InboundMessageRepositoryRequest extends RequestContext {
		
	}
	
	public ServiceLifecycleRequest getMessageServiceRequest();
	public SerialGatewayManagementRequest getSerialGatewayManagementRequest();
	public InboundMessageRepositoryRequest getInboundMessageRepositoryRequest();
}
