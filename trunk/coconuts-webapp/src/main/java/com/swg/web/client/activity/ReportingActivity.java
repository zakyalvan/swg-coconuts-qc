package com.swg.web.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.swg.web.client.place.ReportingPlace;
import com.swg.web.client.presenter.ReportingPresenter;

/**
 * Aktivitas laporan.
 * 
 * @author zakyalvan
 */
public class ReportingActivity extends AbstractActivity implements ReportingPresenter {
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

	}

	@Override
	public ReportingView getView() {
		return null;
	}

	@Override
	public void setReportingPlace(ReportingPlace reportingPlace) {
		
	}
}
