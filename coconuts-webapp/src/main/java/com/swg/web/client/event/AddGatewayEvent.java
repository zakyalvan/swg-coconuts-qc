package com.swg.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.web.client.event.AddGatewayEvent.Handler;
import com.swg.web.shared.proxy.GatewayInfoProxy;

/**
 * Event yang direquest oleh view saat hendak menyimpan gateway ke system.
 * Ingat, mekanisme presentasi dalam aplikasi ini menggunakan MPV passive view.
 * 
 * @author zakyalvan
 */
public class AddGatewayEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onAddGateway(AddGatewayEvent event);
	}
	
	public static Type<Handler> TYPE = new Type<Handler>();
	
	private GatewayInfoProxy gatewayInfoProxy;
	
	public AddGatewayEvent(GatewayInfoProxy gatewayInfoProxy) {
		super();
		this.gatewayInfoProxy = gatewayInfoProxy;
	}

	public GatewayInfoProxy getGatewayInfoProxy() {
		return gatewayInfoProxy;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onAddGateway(this);
	}	
}