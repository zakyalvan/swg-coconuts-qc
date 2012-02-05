package com.swg.web.client.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.Timer;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.web.client.event.WidgetTimerTimeoutEvent;

public class GlobalWidgetTimer extends Timer {
	public static final int DEFAULT_TIMEOUT = 5000;
	
	private Logger logger = Logger.getLogger("GlobalWidgetTimer");	
	private EventBus eventBus;
	
	@Inject
	public GlobalWidgetTimer(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public void run() {
		logger.fine("Global widget timer timeout, publish new event.");
		eventBus.fireEvent(new WidgetTimerTimeoutEvent());
	}
}
