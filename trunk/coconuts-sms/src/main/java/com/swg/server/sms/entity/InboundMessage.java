package com.swg.server.sms.entity;

import java.util.Date;

/**
 * Kontrak untuk jenis entity pesan masuk.
 * 
 * @author zakyalvan
 */
public interface InboundMessage extends Message {
	/**
	 * Status dari pemrosesan inbound message.
	 */
	public static enum Status {
		NEW,
		PROCESSED, 
		FAILED, 
		INVALID 
	}
	
	String getSender();
	String getServiceCenter();
	Date getReceiveDate();
	GatewayInfo getGatewayInfo();
	Date getVersion();
	Status getStatus();
	void setStatus(Status status);
}
