package com.swg.web.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.swg.web.client.event.WidgetTimerTimeoutEvent.Handler;

/**
 * Event yang dipublish oleh global timer pada saat timer tersebut timeout.
 * Di gunakan misalnya untuk trigger reload data yang diperlukan.
 * Menggunakan global timer/schedler biar management timer/scheduler tidak repot/banyak.
 * 
 * @author zakyalvan
 */
public class WidgetTimerTimeoutEvent extends GwtEvent<Handler> {
	public interface Handler extends EventHandler {
		void onWidgetTimerTimeout(WidgetTimerTimeoutEvent timeoutEvent);
	}

	public static final Type<WidgetTimerTimeoutEvent.Handler> TYPE = new Type<WidgetTimerTimeoutEvent.Handler>();
	
	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}
	@Override
	protected void dispatch(Handler handler) {
		handler.onWidgetTimerTimeout(this);
	}
}
