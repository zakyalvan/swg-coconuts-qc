package com.swg.web.client.presenter.impl;

import com.google.gwt.event.shared.ResettableEventBus;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.web.client.event.WidgetTimerTimeoutEvent;
import com.swg.web.client.event.WidgetTimerTimeoutEvent.WidgetTimerTimeoutHandler;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.SiteReportPresenter.SiteReportView;

public class SiteReportPresenter implements MainItemPresenter<SiteReportView>, WidgetTimerTimeoutHandler {
	private static final long serialVersionUID = 5063221867508301694L;

	public interface SiteReportView extends MainItemView {
		public static final String DELETE_TRIGGER_ID = "delete-trigger";
		public static final String RELOAD_TRIGGER_ID = "reload-trigger";
		public static final String AUTORELOAD_TRIGGER_ID = "autoreload-trigger";
		public static final String RELOADPARTIAL_TRIGGER_ID = "reloadpartial-trigger";
	}
	
	public static final String NAME = "site-report";

	private ResettableEventBus resettableEventBus;
	private SiteReportView siteReportView;
	
	private boolean interactive = false;
	
	@Inject
	public SiteReportPresenter(EventBus eventBus, SiteReportView siteReportView) {
		resettableEventBus = new ResettableEventBus(eventBus);
		this.siteReportView = siteReportView;
	}
	
	@Override
	public SiteReportView getView() {
		return siteReportView;
	}
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public boolean isInteractive() {
		return interactive;
	}
	@Override
	public void setInteractive(boolean interactive) {
		this.interactive = interactive;
		if(interactive) {
			resettableEventBus.addHandler(WidgetTimerTimeoutEvent.TYPE, this);
		}
		else {
			resettableEventBus.removeHandlers();
		}
	}

	@Override
	public void onWidgetTimerTimeout(WidgetTimerTimeoutEvent timeoutEvent) {
		
	}
}