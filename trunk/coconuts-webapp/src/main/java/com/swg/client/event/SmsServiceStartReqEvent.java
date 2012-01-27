package com.swg.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.client.event.SmsServiceStartReqEvent.Handler;

/**
 * Ini merupakan event yang fire pada saat merequest untuk start sms service.
 * 
 * @author zakyalvan
 */
public class SmsServiceStartReqEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onSmsServiceStartReq(SmsServiceStartReqEvent event);
	}

	public static Type<Handler> TYPE = new Type<Handler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onSmsServiceStartReq(this);
	}
}
