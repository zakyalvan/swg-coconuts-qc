package com.swg.server.sms.service;

import com.swg.core.QuickCountException;


public class MessageServiceException extends QuickCountException {
	private static final long serialVersionUID = -1049450788260201747L;

	public MessageServiceException() {
		super();
	}

	public MessageServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageServiceException(String message) {
		super(message);
	}

	public MessageServiceException(Throwable cause) {
		super(cause);
	}
}
