package com.swg.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class QuickCountShells extends BorderLayoutContainer {
	private SimpleContainer center;
	
	@Inject
	public QuickCountShells(EventBus eventBus) {
		monitorWindowResize = true;
	    Window.enableScrolling(false);
	    setPixelSize(Window.getClientWidth(), Window.getClientHeight());
	}
	
	@Override
	protected void onWindowResize(int width, int height) {
		setPixelSize(width, height);
	}
}
