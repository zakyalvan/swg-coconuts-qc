package com.swg.web.client.view.web;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.web.client.presenter.impl.VoteRecapitulatePresenter.VoteRecapitulateView;

/**
 * Kelas widget untuk nampilin data rekapitulasi suara per daerah/wilayah.
 * 
 * @author zakyalvan
 */
public class VoteRecapitulateWidget implements VoteRecapitulateView, SelectHandler, SelectionHandler<Item> {
	private EventBus eventBus;
	
	private VerticalLayoutContainer container;
	
	private boolean autoreloadEnabled = true;
	private boolean reloadPartial = true;
	
	@Inject
	public VoteRecapitulateWidget(EventBus eventBus) {
		this.eventBus = eventBus;
		
		container = new VerticalLayoutContainer();
		
		ToolBar toolBar = createToolBar();
		container.add(toolBar, new VerticalLayoutData(1, -1));
		
		// Configure data grid.
	}
	
	@Override
	public void configureTab(TabItemConfig config) {
		config.setText("Rekapitulasi Suara");
		config.setClosable(true);
	}

	@Override
	public Widget asWidget() {
		return container;
	}
	
	@Override
	public void onSelection(SelectionEvent<Item> event) {
		
	}

	@Override
	public void onSelect(SelectEvent event) {
		
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		
		TextButton settingButton = new TextButton("Pengaturan");
		Menu settingMenu = new Menu();
		
		settingMenu.add(new SeparatorMenuItem());
		
		CheckMenuItem autoReloadMenuItem = new CheckMenuItem("Muat Ulang Automatis");
		autoReloadMenuItem.addSelectionHandler(this);
		autoReloadMenuItem.setChecked(reloadPartial);
		settingMenu.add(autoReloadMenuItem);
		
		CheckMenuItem partialReloadMenuItem = new CheckMenuItem("Muat Ulang Parsial");
		partialReloadMenuItem.addSelectionHandler(this);
		partialReloadMenuItem.setChecked(reloadPartial);
		settingMenu.add(partialReloadMenuItem);
		
		MenuItem reloadMenuItem = new MenuItem("Muat Ulang Data");
		reloadMenuItem.addSelectionHandler(this);
		settingMenu.add(reloadMenuItem);
		
		settingButton.setMenu(settingMenu);
		
		toolBar.add(settingButton);
		
		return toolBar;
	}
}
