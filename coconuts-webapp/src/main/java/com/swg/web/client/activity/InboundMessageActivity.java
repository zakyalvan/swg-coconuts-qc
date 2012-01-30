package com.swg.web.client.activity;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.web.client.presenter.InboundMessagePresenter;

public class InboundMessageActivity extends AbstractActivity implements InboundMessagePresenter {
	private Logger logger = Logger.getLogger("InboundMessageActivity");
	private EventBus eventBus; 
	
	private InboundMessagePresenter.View view;
	
	public InboundMessageActivity(EventBus eventBus, InboundMessagePresenter.View view) {
		this.eventBus = eventBus;
		this.view = view;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		logger.info("Render view of inbound-message-presenter.");
	}

	@Override
	public View getView() {
		return view;
	}
}
