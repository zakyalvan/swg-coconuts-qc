package com.swg.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.client.presenter.SmsServiceSettingPresenter;

public class SmsServiceSettingActivity extends AbstractActivity implements SmsServiceSettingPresenter {
	private EventBus eventBus;
	private View view;
	
	@Inject
	public SmsServiceSettingActivity(EventBus eventBus, View view) {
		this.eventBus = eventBus;
		this.view = view;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public View getView() {
		return view;
	}

	@Override
	public void updateServiceSetting() {
		
	}
}
