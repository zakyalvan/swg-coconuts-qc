package com.swg.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.client.event.VoteUpdateEvent.Handler;

/**
 * Event yang di fire pada saat ada perubahan pada data hasil perhitungan 
 * suara yang diterima atau dicatat dalam basis data system.
 * 
 * @author zakyalvan
 */
public class VoteUpdateEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onVoteUpdate(VoteUpdateEvent event);
	}
	
	public static final Type<Handler> TYPE = new Type<Handler>();
	
	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}
	@Override
	protected void dispatch(Handler handler) {
		handler.onVoteUpdate(this);
	}
}
