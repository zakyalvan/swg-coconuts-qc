package com.swg.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.web.client.event.SmsServiceStopEvent.Handler;

public class SmsServiceStopEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onSmsServiceStopEvent(SmsServiceStopEvent event);
	}
	
	public static final Type<Handler> TYPE = new Type<Handler>();

	private boolean success = true;
	private String failureMessage;
	
	public SmsServiceStopEvent() {
		this(true, null);
	}
	public SmsServiceStopEvent(boolean success, String failureMessage) {
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
		handler.onSmsServiceStopEvent(this);
	}
}
