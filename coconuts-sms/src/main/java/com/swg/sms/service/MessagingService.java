package com.swg.sms.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage.MessageStatuses;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.swg.sms.entity.InboundMessage;
import com.swg.sms.entity.InboundMessageBean;
import com.swg.sms.entity.OutboundMessage;
import com.swg.sms.entity.OutboundMessageBean;
import com.swg.sms.entity.repo.InboundMessageRepository;
import com.swg.sms.entity.repo.OutboundMessageRepository;
import com.swg.sms.processor.MessageProcessingException;
import com.swg.sms.processor.MessageProcessor;

/**
 * Message service.
 * 
 * @author zakyalvan
 */
@Service
public class MessagingService implements MessageProcessingService, ServiceLifecycleManager, InitializingBean, ApplicationEventPublisherAware {
	private Logger logger = Logger.getLogger(MessagingService.class);
	
	private org.smslib.Service service = org.smslib.Service.getInstance();
	
	@Autowired(required=true)
	private MessageProcessor messageProcessor;
	
	@Autowired(required=true)
	private InboundMessageRepository inboundMessageRepository;
	
	@Autowired(required=true)
	private OutboundMessageRepository outboundMessageRepository;
	
	private ApplicationEventPublisher applicationEventPublisher;
	
	public void reprocessMessage(InboundMessage inboundMessage) {
		logger.info("Reproses message.");
		inboundMessage = ensurePersistableMessage(inboundMessage);
		inboundMessage.setStatus(InboundMessage.REPROCESS_MESSAGE);
		inboundMessageRepository.save((InboundMessageBean) inboundMessage);
	}
	public void processMessage(InboundMessage inboundMessage) {
		logger.info("Process inbound message.");
		
		if(inboundMessage.getStatus() != InboundMessage.NEW_MESSAGE && inboundMessage.getStatus() != InboundMessage.REPROCESS_MESSAGE) {
			throw new MessagingServiceException("Tidak dapat memproses pesan masuk yang sudah diproses.");
		}
		
		inboundMessage = ensurePersistableMessage(inboundMessage);
		
		inboundMessage.setStatus(InboundMessage.PROCESSING_MESSAGE);
		inboundMessageRepository.save((InboundMessageBean) inboundMessage);
		
		try {
			messageProcessor.processMessage(inboundMessage);
			inboundMessage.setStatus(InboundMessage.PROCESSED_MESSAGE);
			inboundMessageRepository.save((InboundMessageBean) inboundMessage);
		}
		catch(MessageProcessingException e) {
			inboundMessage.setStatus(InboundMessage.FAILED_MESSAGE);
			inboundMessageRepository.save((InboundMessageBean) inboundMessage);
		}
	}
	private InboundMessageBean ensurePersistableMessage(InboundMessage inboundMessage) {
		logger.debug("Ensure persistable message. If not, convert to persistable message.");
		if(!InboundMessageBean.class.isAssignableFrom(inboundMessage.getClass())) {
			
		}
		return (InboundMessageBean) inboundMessage;
	}
	
	/**
	 * Biar tidak ambigu, ini ngirim pesan keluar.
	 * 
	 * @param outboundMessage
	 * @param enforceAlreadyProcessedMessage
	 */
	public void processMessage(OutboundMessage outboundMessage, boolean enforceAlreadyProcessedMessage) {
		logger.info("Process send message.");
		
		/**
		 * Check apakah pesan sudah pernah diproses sebelumnya atau tidak.
		 * Jika sudah, batalkan proses (Throw {@link MessageServiceException}).
		 */
		if(!enforceAlreadyProcessedMessage && outboundMessage.getStatus() != OutboundMessage.NEW_MESSAGE) {
			throw new MessagingServiceException("Tidak dapat mengirim pesan yang sudah dikirim.");
		}
		
		if(!OutboundMessageBean.class.isAssignableFrom(outboundMessage.getClass())) {
			outboundMessage = new OutboundMessageBean(
					outboundMessage.getRecipient(), 
					outboundMessage.getContent());
		}
		
		/**
		 * Ganti status message, biar pada pengecekan berikutnya,
		 * pesan ini tidak disertakan. Dalam kasus prosess pengiriman
		 * yang lebih lama dari delay interval pengechekan (scheduled).
		 */
		outboundMessage.setStatus(OutboundMessage.SEND_MESSAGE);

		/**
		 * Simpan perubahan.
		 */
		outboundMessageRepository.save((OutboundMessageBean) outboundMessage);
		
		org.smslib.OutboundMessage outMessage = new org.smslib.OutboundMessage();
		
		outMessage.setId(outboundMessage.toString());
		outMessage.setText(outboundMessage.getContent());
		outMessage.setRecipient(outboundMessage.getRecipient());
		
		service.queueMessage(outMessage);
	}
	public void processMessage(OutboundMessage outboundMessage) {
		this.processMessage(outboundMessage, false);
	}
	
	@Scheduled(fixedDelay=5000)
	synchronized void checkAndProcessInboundMessage() {
		logger.info("Check and process inbound message.");
		
		if(inboundMessageRepository.countNewMessages() > 0) {
			List<InboundMessage> msgs = inboundMessageRepository.findByStatusIn(
					Arrays.<Integer> asList(InboundMessage.NEW_MESSAGE, InboundMessage.REPROCESS_MESSAGE));
			
			for(InboundMessage msg : msgs) {
				processMessage(msg);
			}
		}
	}
	@Scheduled(fixedDelay=5000)
	synchronized void checkAndProcessOutboundMessage() {
		logger.info("Check and process outbound message.");
		if(outboundMessageRepository.countNewMessages() > 0) {
			List<OutboundMessage> outboundMessages = outboundMessageRepository.findAllByStatus(OutboundMessage.NEW_MESSAGE);
			logger.info("Found " + outboundMessages.size() + " item(s) pesan keluar baru untuk dikirim.");
			for(OutboundMessage outboundMessage : outboundMessages) {
				processMessage(outboundMessage);
			}
		}
	}

	@Override
	public void startService() {
		try {
			SerialModemGateway gateway = new SerialModemGateway("HuaweiModem", "COM4", 115200, "Huawei", "E160");
			service.addGateway(gateway);
			service.startService();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (GatewayException e) {
			e.printStackTrace();
		} catch (SMSLibException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopService() {
		try {
			service.stopService();
			service.removeGateway(service.getGateway("HuaweiModem"));
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (GatewayException e) {
			e.printStackTrace();
		} catch (SMSLibException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private class InboundMessageNotification implements IInboundMessageNotification {
		private Logger logger = Logger.getLogger(InboundMessageNotification.class);
		@Override
		public void process(AGateway gateway, MessageTypes messageTypes, org.smslib.InboundMessage inboundMessage) {
			if(messageTypes == MessageTypes.INBOUND) {
				logger.info("Notifikasi untuk pesan masuk baru diterima.");
				try {
					gateway.deleteMessage(inboundMessage);
					
					InboundMessageBean newInboundMessage = new InboundMessageBean();
					newInboundMessage.setSender(inboundMessage.getOriginator());
					newInboundMessage.setContent(inboundMessage.getText());
					newInboundMessage.setStatus(InboundMessage.NEW_MESSAGE);
					
					inboundMessageRepository.save(newInboundMessage);
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
	private class OutboundMessageNotification implements IOutboundMessageNotification {
		private Logger logger = Logger.getLogger(OutboundMessageNotification.class);
		@Override
		public void process(AGateway gateway, org.smslib.OutboundMessage outboundMessage) {
			logger.info("Notification untuk sms keluar yang selesai diproses.");
			
			OutboundMessageBean newOutboundMessage = outboundMessageRepository.findOne(Integer.valueOf(outboundMessage.getId()));
			if(outboundMessage.getMessageStatus() == MessageStatuses.SENT)
				newOutboundMessage.setStatus(OutboundMessage.SENT_MESSAGE);
			else if(outboundMessage.getMessageStatus() == MessageStatuses.UNSENT)
				newOutboundMessage.setStatus(OutboundMessage.UNSENT_MESSAGE);
			else if(outboundMessage.getMessageStatus() == MessageStatuses.FAILED)
				newOutboundMessage.setStatus(OutboundMessage.FAILED_MESSAGE);

			outboundMessageRepository.save(newOutboundMessage);
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("Set inbound dan outbound notification ke service.");
		service.setInboundMessageNotification(new InboundMessageNotification());
		service.setOutboundMessageNotification(new OutboundMessageNotification());
	}
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {	
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
