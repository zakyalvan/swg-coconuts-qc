package com.swg.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.client.event.AddGatewayRequestEvent.Handler;

/**
 * Event yang direquest oleh view saat hendak menambah gateway ke system.
 * Ingat, mekanisme presentasi dalam aplikasi ini menggunakan MPV passive view.
 * 
 * @author zakyalvan
 */
public class AddGatewayRequestEvent extends GwtEvent<Handler> {
	public enum SupportedGatewayType {
		SERIAL_MODEM
	}
	
	public interface Handler extends EventHandler {
		void onAddGatewayRequest(AddGatewayRequestEvent event);
	}
	
	public static Type<Handler> TYPE = new Type<Handler>();

	private SupportedGatewayType type;
	
	public AddGatewayRequestEvent(SupportedGatewayType type) {
		super();
		this.type = type;
	}

	public SupportedGatewayType getType() {
		return type;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onAddGatewayRequest(this);
	}	
}