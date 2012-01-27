package com.swg.sms.entity;

public interface OutboundMessage {
	public static final int NEW_MESSAGE = 1;
	public static final int SEND_MESSAGE = 2;
	public static final int SENT_MESSAGE = 3;
	public static final int UNSENT_MESSAGE = 4;
	public static final int FAILED_MESSAGE = 5;
	
	Integer getId();
	String getReceiver();
	String getContent();
	int getStatus();
	void setStatus(int status);
}
