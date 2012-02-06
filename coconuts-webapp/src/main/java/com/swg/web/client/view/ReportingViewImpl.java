package com.swg.web.client.view;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.swg.web.client.presenter.ReportingPresenter.ReportingView;

public class ReportingViewImpl extends SimpleContainer implements ReportingView {
	private EventBus eventBus;
	
	@Inject
	public ReportingViewImpl(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}
}
