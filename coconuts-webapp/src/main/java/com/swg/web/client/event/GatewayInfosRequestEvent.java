package com.swg.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.web.client.event.GatewayInfosRequestEvent.Handler;

public class GatewayInfosRequestEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		public void onGatewayInfosRequest(GatewayInfosRequestEvent event);
	}
	
	public static final Type<Handler> TYPE = new Type<Handler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onGatewayInfosRequest(this);
	}
}
