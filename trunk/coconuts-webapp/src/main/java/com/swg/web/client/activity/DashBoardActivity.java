package com.swg.web.client.activity;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.swg.web.client.presenter.DashBoardPresenter;

public class DashBoardActivity extends AbstractActivity implements DashBoardPresenter {
	private Logger logger = Logger.getLogger("DashBoardView");
	
	private View view;
	
	@Inject(optional=true)
	public DashBoardActivity(View view) {
		logger.setLevel(Level.FINEST);
		this.view = view;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
	}

	@Override
	public View getView() {
		return view;
	}
}
