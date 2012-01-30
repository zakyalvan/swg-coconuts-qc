package com.swg.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.web.client.event.SmsServiceStopReqEvent.Handler;

/**
 * Ini merupakan event yang fire pada saat merequest untuk stop sms service.
 * 
 * @author zakyalvan
 */
public class SmsServiceStopReqEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onSmsServiceStopReq(SmsServiceStopReqEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onSmsServiceStopReq(this);
	}
}
