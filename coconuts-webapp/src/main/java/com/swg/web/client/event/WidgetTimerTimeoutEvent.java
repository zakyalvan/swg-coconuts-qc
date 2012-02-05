package com.swg.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.web.client.event.WidgetTimerTimeoutEvent.WidgetTimerTimeoutHandler;

/**
 * Event yang dipublish oleh global timer pada saat timer tersebut timeout.
 * Di gunakan misalnya untuk trigger reload data yang diperlukan.
 * Menggunakan global timer/schedler biar management timer/scheduler tidak repot/banyak.
 * 
 * @author zakyalvan
 */
public class WidgetTimerTimeoutEvent extends GwtEvent<WidgetTimerTimeoutHandler> {
	public interface WidgetTimerTimeoutHandler extends EventHandler {
		void onWidgetTimerTimeout(WidgetTimerTimeoutEvent timeoutEvent);
	}

	public static final Type<WidgetTimerTimeoutEvent.WidgetTimerTimeoutHandler> TYPE = new Type<WidgetTimerTimeoutEvent.WidgetTimerTimeoutHandler>();
	
	@Override
	public Type<WidgetTimerTimeoutHandler> getAssociatedType() {
		return TYPE;
	}
	@Override
	protected void dispatch(WidgetTimerTimeoutHandler handler) {
		handler.onWidgetTimerTimeout(this);
	}
}
