package com.swg.server.sms.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.GatewayException;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.swg.server.sms.entity.GatewayInfo;
import com.swg.server.sms.entity.InboundMessage;
import com.swg.server.sms.entity.InboundMessageBean;
import com.swg.server.sms.entity.SerialGatewayInfo;
import com.swg.server.sms.repo.GatewayInfoRepository;
import com.swg.server.sms.repo.InboundMessageRepository;
import com.swg.server.sms.service.GatewayFactory.CantCreateGatewayException;

/**
 * Implementasi default dari kelas {@link MessageService}.
 * Dalam aplikasi ini, back-end service menggunakan 
 * 
 * @author zakyalvan
 */
@Service("messageService")
public class MessageServiceImpl implements GatewayManager<GatewayInfo>, MessageService, InitializingBean, ApplicationEventPublisherAware {
	private Logger logger = Logger.getLogger(getClass());
	
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired(required=true)
	private InboundMessageRepository inboundMessageRepository;
	
	//@Autowired(required=true)
	private MessageProcessor<InboundMessage> inboundMessageProcessor;
	
	@Autowired(required=true)
	private GatewayInfoRepository gatewayInfoRepository;
	
	@Autowired(required=true)
	private GatewayFactory<? extends AGateway> gatewayFactory;
	
	private Set<Class<? extends GatewayInfo>> supportedGatewayInfoTypes = new HashSet<Class<? extends GatewayInfo>>();
	
	/**
	 * Inilah object worker sebenarnya bertugas ngirim dan nerima sms.
	 */
	private org.smslib.Service service = org.smslib.Service.getInstance();
	
	private MessageServiceStatus serviceStatus;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		/**
		 * Register jenis gateway info yang di support oleh system.
		 */
		supportedGatewayInfoTypes.add(SerialGatewayInfo.class);
		
		/**
		 * Register listener untuk event notification dari service sms.
		 */
		service.setInboundMessageNotification(new InboundMessageNotification());
		service.setOutboundMessageNotification(new OutboundMessageNotification());
		service.setGatewayStatusNotification(new GatewayStatusNotification());
	}

	@Override
	public Set<Class<? extends GatewayInfo>> getSupportedGatewayInfoTypes() {
		return supportedGatewayInfoTypes;
	}
	@Override
	public void setSupportedGatewayInfoTypes(Set<Class<? extends GatewayInfo>> supportedGatewayInfoTypes) {
		this.supportedGatewayInfoTypes.addAll(supportedGatewayInfoTypes);
	}

	@Override
	public void registerGateway(GatewayInfo gateway) throws MessageServiceException {
	}

	@Override
	public GatewayInfo getGateway(String id) {
		return null;
	}

	@Override
	public Set<GatewayInfo> getGateways() {
		return null;
	}

	@Override
	public Set<GatewayInfo> getGateways(Integer capability) {
		return null;
	}

	@Override
	public GatewayInfo enableGateway(String id) throws MessageServiceException {
		return null;
	}

	@Override
	public GatewayInfo disableGateway(String id) throws MessageServiceException {
		logger.info("Disable an gateway with id : " + id);
		if(gatewayInfoRepository.isRegisteredGateway(id)) {
			GatewayInfo gatewayInfo = gatewayInfoRepository.findOne(id);
			gatewayInfo.setEnabled(false);
			return gatewayInfoRepository.save(gatewayInfo);
		}
		else {
			throw new MessageServiceException("Gateway info with id : " + id + " tidak ditemukan.");
		}
	}
	

	@Override
	public GatewayInfo removeGateway(String id) throws MessageServiceException {
		return null;
	}

	@Override
	public void addServiceSetting(String key, String value) {

	}

	@Override
	public String getServiceSetting(String key) {
		return null;
	}

	@Override
	public void setServiceSettings(Map<String, String> settings) {

	}

	@Override
	public Map<String, String> getServiceSettings() {
		return null;
	}

	@Override
	public void startService() throws MessageServiceException {
		logger.info("Memulai service server sms, pertama create dan register gateway.");
		
		Set<AGateway> gateways = new HashSet<AGateway>();
		for(Class<? extends GatewayInfo> supportedGatewayInfoType : supportedGatewayInfoTypes) {
			try {
				logger.info("Query gateway info jenis : " + supportedGatewayInfoType);
				List<? extends GatewayInfo> gatewayInfos = gatewayInfoRepository.findAll(supportedGatewayInfoType);
				logger.info("Ditemukan record gateway info jenis " + supportedGatewayInfoType + " sejumlah : " + gatewayInfos.size() + " record. " + gatewayInfos);
				
				for(GatewayInfo gatewayInfo : gatewayInfos) {
					try {
						AGateway gateway = gatewayFactory.createGateway(gatewayInfo);
						gateways.add(gateway);
					}
					catch(CantCreateGatewayException e) {
						logger.error("Terjadi kesalahan dalam gateway factory : " + e.getMessage());
						e.printStackTrace();
						
						if(e.getCode() == CantCreateGatewayException.FACTORY_CANT_HANDLE) {
							throw new MessageServiceException("Tidak ditemukan gateway factory untuk gateway info : " + gatewayInfo, e);
						}
						else if(e.getCode() == CantCreateGatewayException.INVALID_REQUIRED_PARAM) {
							throw new MessageServiceException("Terdapat kesalahan dalam gateway info : " + gatewayInfo, e);
						}
					}
				}
			}
			catch(RuntimeException e) {
				logger.info("Starting service gagal.", e);
			}
		}
		if(gateways.size() == 0) {
			logger.error("Tidak ditemukan gateway untuk server sms, start service gagal.");
			throw new MessageServiceException("Tidak ditemukan gateway untuk server sms.");
		}

		try {
			for(AGateway gateway : gateways) {
				service.addGateway(gateway);
			}
			service.startService();

			serviceStatus.setStarted(true);
			serviceStatus.setStartTime(new Date());
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		} catch (GatewayException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		} catch (SMSLibException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		}
	}

	@Override
	public void stopService() throws MessageServiceException {
		logger.info("Stoping service, sebelumnya remove seluruh gateway yang terdaftar.");
		try {
			Collection<AGateway> gateways = service.getGateways();
			for(AGateway gateway : gateways) {
				service.removeGateway(gateway);
			}	
			service.stopService();
			serviceStatus.setStarted(false);
		} catch (TimeoutException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		} catch (GatewayException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		} catch (SMSLibException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new MessageServiceException(e);
		}
	}

	@Override
	public MessageServiceStatus getStatus() {
		return serviceStatus;
	}

	@Override
	public InboundMessageRepository getInboundMessageRepository() {
		return inboundMessageRepository;
	}

	@Override
	public void setInboundMessageProcessor(MessageProcessor<InboundMessage> inboundMessageProcessor) {
		this.inboundMessageProcessor = inboundMessageProcessor;
	}


	@Override
	public InboundMessageBean getInboundMessage(Long id) {
		return null;
	}
	@Override
	public InboundMessageBean getInboundMessage(String sender) {
		return null;
	}
	@Override
	public Set<InboundMessageBean> getInboundMessages() {
		return null;
	}
	@Override
	public Set<InboundMessageBean> getInboundMessages(Date start) {
		return null;
	}
	@Override
	public Set<InboundMessageBean> getInboundMessages(Date start, Date end) {
		return null;
	}
	
	/**
	 * Implementasi dari {@link ApplicationEventPublisherAware}.
	 * Sesuai namanya, ya taulah fungsinya.
	 */
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
	
	/**
	 * Implementasi untuk {@link IInboundMessageNotification}.
	 * Object dari kelas notifikasi ini hanya bertugas menyimpan pesan yang
	 * diterima melalui gateway (Penyimpanan menggunakan dalam basis data internal
	 * system sehingga harus dilakukan konversi dari format smslib ke format internal).
	 * Selain itu menghapus data pesan masuk dalam gateway.
	 * 
	 * @author zakyalvan
	 */
	private class InboundMessageNotification implements IInboundMessageNotification {
		private Logger logger = Logger.getLogger(InboundMessageNotification.class);
		
		@Override
		public void process(AGateway gateway, MessageTypes msgType, org.smslib.InboundMessage msg) {
			logger.info("Pesan baru dengan type : " + msgType + ", received via gateway " + msg.getGatewayId());
			if(msgType == MessageTypes.INBOUND) {
				logger.info("Pesan masuk baru diterima, catat dalam database.");
				
				GatewayInfo gatewayInfo = gatewayInfoRepository.findOne(msg.getGatewayId());
				InboundMessageBean inboundMessage = new InboundMessageBean(msg.getText(), msg.getOriginator(), gatewayInfo, msg.getSmscNumber(), msg.getDate(), InboundMessage.Status.NEW);

				logger.info("Message yang diterima : " + inboundMessage);
				try {
					gateway.deleteMessage(msg);
					inboundMessageRepository.saveAndFlush(inboundMessage);
				} catch (TimeoutException e) {
					e.printStackTrace();
				} catch (GatewayException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Implementasi dari {@link IOutboundMessageNotification}.
	 * Tugasnya adalah mengupdate data status pesan keluar yang diterkirim/gagal.
	 * 
	 * @author zakyalvan
	 *
	 */
	private class OutboundMessageNotification implements IOutboundMessageNotification {
		@Override
		public void process(AGateway gateway, OutboundMessage msg) {
			logger.info("Pesan keluar terkirim.");
		}
	}
	private class GatewayStatusNotification implements IGatewayStatusNotification {
		@Override
		public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {
			logger.info("Perubahan status gateway (dari "+oldStatus+ " ke " + newStatus + " ), catat dalam audit gateway.");
		}
	}
}
