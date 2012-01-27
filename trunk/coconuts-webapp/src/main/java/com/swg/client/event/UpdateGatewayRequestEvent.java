package com.swg.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.client.event.UpdateGatewayRequestEvent.Handler;
import com.swg.shared.proxy.GatewayInfoProxy;

/**
 * Event yang direquest oleh view saat hendak menambah gateway ke system.
 * Ingat, mekanisme presentasi dalam aplikasi ini menggunakan MPV passive view.
 * 
 * @author zakyalvan
 */
public class UpdateGatewayRequestEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onUpdateGatewayRequest(UpdateGatewayRequestEvent event);
	}
	
	public static Type<Handler> TYPE = new Type<Handler>();

	private GatewayInfoProxy willUpdate;
	
	public UpdateGatewayRequestEvent(GatewayInfoProxy willUpdate) {
		super();
		this.willUpdate = willUpdate;
	}

	public GatewayInfoProxy getWillUpdate() {
		return willUpdate;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onUpdateGatewayRequest(this);
	}
}