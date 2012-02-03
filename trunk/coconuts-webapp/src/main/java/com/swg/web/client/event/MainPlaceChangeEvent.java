package com.swg.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.web.client.event.MainPlaceChangeEvent.Handler;

public class MainPlaceChangeEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onMainPlaceChange(MainPlaceChangeEvent event);
	}
	
	public static final Type<Handler> TYPE = new Type<Handler>();
	
	public final String name;
	
	public MainPlaceChangeEvent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}
	@Override
	protected void dispatch(Handler handler) {
		handler.onMainPlaceChange(this);
	}
}
