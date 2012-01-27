package com.swg.server.sms.service;

import com.swg.server.sms.entity.Message;

/**
 * Kontrak untuk stategy pemrosesan pesan.
 * 
 * @author zakyalvan
 */
public interface MessageProcessor<N extends Message> {
	void processMessage(N message) throws MessageServiceException;
}