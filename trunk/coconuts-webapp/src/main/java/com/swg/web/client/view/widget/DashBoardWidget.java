package com.swg.web.client.view.widget;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.swg.web.client.presenter.DashBoardPresenter.View;

/**
 * Ini widget dari activity utama atau widget yang ditampilkan 
 * pertama kali user mengakses aplikasi.
 * 
 * @author zakyalvan
 */
public class DashBoardWidget implements View {
	private Logger logger = Logger.getLogger("DashBoardWidget");
	
	private EventBus eventBus;
	private SimpleContainer container;
	
	@Inject(optional=false)
	public DashBoardWidget(EventBus eventBus) {
		logger.setLevel(Level.FINEST);
		this.eventBus = eventBus;
	}
	
	@Override
	public void configureTab(TabItemConfig config) {
		config.setText("Dashboard Aplikasi");
		config.setClosable(false);
	}

	@Override
	public Widget asWidget() {
		container = new SimpleContainer();
		
		return container;
	}
}