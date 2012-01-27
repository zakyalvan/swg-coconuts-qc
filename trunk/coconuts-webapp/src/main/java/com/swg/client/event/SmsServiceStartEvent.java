package com.swg.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.swg.client.event.SmsServiceStartEvent.Handler;

public class SmsServiceStartEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onSmsServiceStartEvent(SmsServiceStartEvent event);
	}
	
	public static final Type<Handler> TYPE = new Type<Handler>();

	private boolean success = true;
	private String failureMessage;
	
	public SmsServiceStartEvent() {
		this(true, null);
	}
	public SmsServiceStartEvent(boolean success, String failureMessage) {
		this.success = success;
		this.failureMessage = failureMessage;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public String getFailureMessage() {
		return failureMessage;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onSmsServiceStartEvent(this);
	}
	
	public static final HandlerRegistration register(EventBus eventBus, Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}
}
