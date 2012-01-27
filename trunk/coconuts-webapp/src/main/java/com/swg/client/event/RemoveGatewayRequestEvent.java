package com.swg.client.event;

import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.client.event.RemoveGatewayRequestEvent.Handler;
import com.swg.shared.proxy.GatewayInfoProxy;

/**
 * Event yang direquest oleh view saat hendak menghapus gateway ke system.
 * Ingat, mekanisme presentasi dalam aplikasi ini menggunakan MPV passive view.
 * 
 * @author zakyalvan
 */
public class RemoveGatewayRequestEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onRemoveGatewayRequest(RemoveGatewayRequestEvent event);
	}
	
	public static Type<Handler> TYPE = new Type<Handler>();

	private List<GatewayInfoProxy> records;
	
	public RemoveGatewayRequestEvent(List<GatewayInfoProxy> records) {
		this.records = records;
	}

	public List<GatewayInfoProxy> getRecords() {
		return records;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onRemoveGatewayRequest(this);
	}	
}