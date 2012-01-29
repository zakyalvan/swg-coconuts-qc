package com.swg.server.sms.service;

import org.aspectj.bridge.Message;

/**
 * Kontrak untuk stategy pemrosesan pesan.
 * 
 * @author zakyalvan
 */
public interface MessageProcessor<N extends Message> {
	void processMessage(N message) throws MessageServiceException;
}