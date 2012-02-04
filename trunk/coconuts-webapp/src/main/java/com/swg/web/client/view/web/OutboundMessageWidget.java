package com.swg.web.client.view.web;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter.OutboundMessageView;

public class OutboundMessageWidget implements OutboundMessageView {
	private Logger logger = Logger.getLogger("OutboundMessageWidget");
	
	private EventBus eventBus;
	private VerticalLayoutContainer widget;
	
	@Inject
	public OutboundMessageWidget(EventBus eventBus) {
		this.eventBus = eventBus;
		widget = new VerticalLayoutContainer();
	}
	
	@Override
	public void configureTab(TabItemConfig config) {
		config.setText("Pesan Keluar");
		config.setClosable(true);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
