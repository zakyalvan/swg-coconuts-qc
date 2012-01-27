package com.swg.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.client.event.SmsServiceSettingRequestEvent.Handler;

public class SmsServiceSettingRequestEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onSmsServiceSettingRequest(SmsServiceSettingRequestEvent event);
	}
	
	public static final Type<Handler> TYPE = new Type<Handler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onSmsServiceSettingRequest(this);
	}
}
