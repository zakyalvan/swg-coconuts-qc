package com.swg.client.view.widget;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.swg.client.event.SmsServiceSettingRequestEvent;
import com.swg.client.presenter.SmsServiceSettingPresenter;

public class SmsServiceSettingWidget implements SmsServiceSettingPresenter.View {
	private EventBus eventBus;
	
	private TextButton updateButton;
	private TextButton cancelButton;
	
	private SimpleContainer container;
	
	@Inject(optional=false)
	public SmsServiceSettingWidget(EventBus eventBus) {
		this.eventBus = eventBus;
		configure();
	}
	
	protected void configure() {
		SelectHandler selectHandler = new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				TextButton sourceButton = (TextButton) event.getSource();
				if(sourceButton.getId().equals(UPDATE_SETTING_BTN_ID)) {
					eventBus.fireEvent(new SmsServiceSettingRequestEvent());
				}
			}
		};
		updateButton = new TextButton("Update Setting");
		updateButton.setId(UPDATE_SETTING_BTN_ID);
		updateButton.addSelectHandler(selectHandler);
		
		cancelButton = new TextButton("Batal");
		cancelButton.setId(CANCEL_SETTING_BTN_ID);
		
		container = new SimpleContainer();
	}
	
	@Override
	public void configureWindow(Window window) {
		window.setHeadingText("Service Setting");
		window.setTitle("Service Setting Window");
		window.setShadow(true);
		window.setClosable(false);
		window.setModal(true);
		
		window.setButtonAlign(BoxLayoutPack.END);
		window.setMinButtonWidth(75);
		window.getButtonBar().add(updateButton);
		window.getButtonBar().add(cancelButton);
	}

	@Override
	public Widget asWidget() {
		return container;
	}
}
