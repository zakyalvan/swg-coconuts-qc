package com.swg.server.sms.service;

import com.swg.server.sms.entity.InboundMessage;

/**
 * Exception yang dilempar pada saat parser tidak dapat meparse pesan masuk.
 * 
 * @author zakyalvan
 */
public class CantParseMessageException extends MessageServiceException {
	private static final long serialVersionUID = 3745020352169609781L;
	
	private InboundMessage inboundMessage;

	public CantParseMessageException(InboundMessage inboundMessage) {
		super();
		this.inboundMessage = inboundMessage;
	}

	public CantParseMessageException(String message, InboundMessage inboundMessage, Throwable cause) {
		super(message, cause);
		this.inboundMessage = inboundMessage;
	}

	public CantParseMessageException(String message, InboundMessage inboundMessage) {
		super(message);
		this.inboundMessage = inboundMessage;
	}

	public CantParseMessageException(InboundMessage inboundMessage, Throwable cause) {
		super(cause);
	}

	/**
	 * Retrieve message yang gagal diparsing.
	 * 
	 * @return
	 */
	public InboundMessage getInboundMessage() {
		return inboundMessage;
	}
}
