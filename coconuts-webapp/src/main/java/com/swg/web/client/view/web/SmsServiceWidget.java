package com.swg.web.client.view.web;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.web.client.presenter.impl.SmsServicePresenter.SmsServiceView;

public class SmsServiceWidget implements SmsServiceView, SelectHandler {
	private EventBus eventBus;
	
	private VerticalLayoutContainer container;
	
	@Inject
	public SmsServiceWidget(EventBus eventBus) {
		this.eventBus = eventBus;
		
		container = new VerticalLayoutContainer();
		container.add(createToolBar());
	}
	
	@Override
	public void configureTab(TabItemConfig config) {
		config.setText("Pengaturan Sms");
		config.setClosable(true);
	}
	@Override
	public Widget asWidget() {
		return container;
	}
	@Override
	public void notifyServiceStarted() {

	}
	@Override
	public void notifyServiceStartingFailed(Throwable failureCause) {

	}
	@Override
	public void notifyServiceStoped() {

	}
	@Override
	public void notifyServiceStopingFailed(Throwable failureCause) {

	}
	@Override
	public void onSelect(SelectEvent event) {
		if(event.getSource() instanceof TextButton) {
			TextButton sourceButon = (TextButton) event.getSource();
			if(sourceButon.getId().equals(START_SERVICE_TRIGGER_ID)) {
				// Do start service here.
			}
			else if(sourceButon.getId().equals(STOP_SERVICE_TRIGGER_ID)) {
				// Do stop service here.
			}
		}
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		
		TextButton startButton = new TextButton("Start Service");
		startButton.setId(START_SERVICE_TRIGGER_ID);
		startButton.addSelectHandler(this);
		
		TextButton stopButton = new TextButton("Stop Service");
		stopButton.setId(STOP_SERVICE_TRIGGER_ID);
		stopButton.addSelectHandler(this);
		
		toolBar.add(startButton);
		toolBar.add(stopButton);
		
		return toolBar;
	}
}
