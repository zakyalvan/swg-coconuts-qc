package com.swg.server.sms.service;

import org.springframework.context.ApplicationEvent;

public class InboundMessageReceivedEvent extends ApplicationEvent {
	private static final long serialVersionUID = 4705897142274679294L;

	public InboundMessageReceivedEvent(Object source) {
		super(source);
	}
}
