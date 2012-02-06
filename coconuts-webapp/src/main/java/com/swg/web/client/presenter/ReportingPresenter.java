package com.swg.web.client.presenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.swg.web.client.place.ReportingPlace;
import com.swg.web.client.presenter.ReportingPresenter.ReportingView;

public interface ReportingPresenter extends Presenter<ReportingView> {
	public interface ReportingView extends IsWidget {
		
	}
	
	void setReportingPlace(ReportingPlace reportingPlace);
}
