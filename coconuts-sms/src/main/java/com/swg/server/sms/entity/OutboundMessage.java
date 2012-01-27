package com.swg.server.sms.entity;

import java.util.Date;

/**
 * Kontrak untuk pesan keluar.
 * 
 * @author zakyalvan
 */
public interface OutboundMessage extends Message {
	public static enum Status {
		NEW,
		RETRY,
		SENT,
		FAILED
	}
	
	String getRecipient();
	GatewayInfo getGatewayInfo();
	Status getStatus();
	void setStatus(Status status);
	Date getCreatedDate();
	Date getSentDate();
	void setSentDate(Date sentDate);
}
