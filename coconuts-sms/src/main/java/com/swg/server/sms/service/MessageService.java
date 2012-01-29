package com.swg.server.sms.service;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.swg.sms.entity.InboundMessageBean;
import com.swg.sms.entity.repo.InboundMessageRepository;

/**
 * Kontrak untuk messaging service, inbound dan outbound.
 * 
 * @author zakyalvan
 */
public interface MessageService {
	public class MessageServiceStatus implements Serializable {
		private static final long serialVersionUID = -5183685729177221269L;
		
		private boolean started = false;
		private Date startTime;
		
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public boolean isStarted() {
			return started;
		}
		public void setStarted(boolean started) {
			this.started = started;
		}
	}

	
	
	
	void addServiceSetting(String key, String value);
	String getServiceSetting(String key);
	void setServiceSettings(Map<String, String> settings);
	Map<String, String> getServiceSettings();
	
	/**
	 * Start service.
	 * 
	 * @throws MessageServiceException
	 */
	void startService() throws MessageServiceException;
	
	/**
	 * Stop service.
	 * 
	 * @throws MessageServiceException
	 */
	void stopService() throws MessageServiceException;
	
	MessageServiceStatus getStatus();
	
	/**
	 * Retrieve repository pesan masuk.
	 * 
	 * @return {@link InboundMessageRepository}
	 */
	InboundMessageRepository getInboundMessageRepository();
	
	
	/**
	 * Retrieve {@link InboundMessageBean} berdasarkan id-nya.
	 * 
	 * @param Integer id
	 * @return {@link InboundMessageBean}
	 */
	InboundMessageBean getInboundMessage(Long id);
	
	/**
	 * Retrieve {@link InboundMessageBean} berdasarkan nomor pengirimya.
	 * 
	 * @param String sender
	 * @return {@link InboundMessageBean}
	 */
	InboundMessageBean getInboundMessage(String sender);
	
	/**
	 * Retrieve inbound message yang pernah diterima atau tercatat dalam system.
	 * 
	 * @return
	 */
	Set<InboundMessageBean> getInboundMessages();
	
	/**
	 * Retrieve inbound message yang pernah diterima atau tercatat dalam system
	 * mulai dari waktu terntentu (start parameter).
	 * 
	 * @param {@link Date} start
	 * @return 
	 */
	Set<InboundMessageBean> getInboundMessages(Date start);
	
	/**
	 * Retrieve inbound message yang pernah diterima atau tercatat dalam system
	 * dalam batas periode tertentu.
	 * 
	 */
	Set<InboundMessageBean> getInboundMessages(Date start, Date end);
}