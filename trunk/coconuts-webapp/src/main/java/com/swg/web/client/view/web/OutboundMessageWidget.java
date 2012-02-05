package com.swg.web.client.view.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter.OutboundMessageView;
import com.swg.web.shared.proxy.OutboundMessageProxy;

public class OutboundMessageWidget implements OutboundMessageView, SelectHandler, SelectionHandler<Item> {
	public interface OutboundMessagePropertyAccess extends PropertyAccess<OutboundMessageProxy> {
		@Path("id")
		ModelKeyProvider<OutboundMessageProxy> key();
		ValueProvider<OutboundMessageProxy, Integer> id();
		ValueProvider<OutboundMessageProxy, String> content();
		ValueProvider<OutboundMessageProxy, String> recipient();
		ValueProvider<OutboundMessageProxy, Integer> status();
	}
	
	private Logger logger = Logger.getLogger("OutboundMessageWidget");
	
	private EventBus eventBus;
	private VerticalLayoutContainer widget;
	
	private boolean autoreloadEnabled = true;
	private boolean autoreloadPartial = false;
	private Date lastDataVersion;
	
	private ListStore<OutboundMessageProxy> dataStore;
	private CheckBoxSelectionModel<OutboundMessageProxy> selectionModel;
	private Grid<OutboundMessageProxy> dataGrid;
	
	private OutboundMessagePropertyAccess propertyAccess = GWT.create(OutboundMessagePropertyAccess.class);
	
	@Inject
	public OutboundMessageWidget(EventBus eventBus) {
		this.eventBus = eventBus;
		
		widget = new VerticalLayoutContainer();
		
		widget.add(createToolBar());
		
		configureDataGrid();
		widget.add(dataGrid);
	}
	
	@Override
	public void configureTab(TabItemConfig config) {
		config.setText("Pesan Keluar");
		config.setClosable(true);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public boolean isAutoreloadEnabled() {
		return autoreloadEnabled;
	}

	@Override
	public boolean isAutoreloadPartial() {
		return autoreloadPartial;
	}

	@Override
	public Date getLastDataVersion() {
		return lastDataVersion;
	}

	@Override
	public void setDatas(List<OutboundMessageProxy> datas) {
		setDatas(datas, false);
	}

	@Override
	public void setDatas(List<OutboundMessageProxy> datas, boolean partial) {
		/**
		 * Copast dari InboundMessageWidget.setDatas
		 */
		if(partial) {
			List<OutboundMessageProxy> storeDatas = dataStore.getAll();
			for(OutboundMessageProxy data : datas) {
				for(OutboundMessageProxy storeData : storeDatas) {
					if(data.getId() == storeData.getId()) {
						dataStore.remove(storeData);
					}
				}
			}
		}
		else {
			dataStore.clear();
		}
		
		for(OutboundMessageProxy data : datas) {
			if(lastDataVersion == null) {
				lastDataVersion = data.getVersion();
			}
			else {
				if(data.getVersion().after(lastDataVersion)) {
					lastDataVersion = data.getVersion();
				}
			}
		}
		dataStore.addAll(datas);
	}

	@Override
	public void clearDatas() {
		
	}

	@Override
	public void onSelection(SelectionEvent<Item> event) {
		if(event.getSelectedItem().getId().equals(AUTORELOAD_TRIGGER_ID)) {
			autoreloadEnabled = ((CheckMenuItem) event.getSelectedItem()).isChecked();
		}
		else if(event.getSelectedItem().getId().equals(AUTORELOAD_TRIGGER_ID)) {
			// Do reload here (or publish reload request event).
		}
	}

	@Override
	public void onSelect(SelectEvent event) {
		
	}
	
	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		
		TextButton createButton = new TextButton("Tulis Pesan");
		createButton.setId(COMPOSE_TRIGGER_ID);
		createButton.addSelectHandler(this);
		
		TextButton resendButton = new TextButton("Kirim Ulang");
		resendButton.setId(RESEND_TRIGGER_ID);
		resendButton.addSelectHandler(this);
		
		TextButton settingButton = new TextButton("Pengaturan");
		Menu settingMenu = new Menu();
		
		settingMenu.add(new SeparatorMenuItem());
		
		CheckMenuItem autoReloadMenuItem = new CheckMenuItem("Muat Ulang Automatis");
		autoReloadMenuItem.setId(AUTORELOAD_TRIGGER_ID);
		autoReloadMenuItem.addSelectionHandler(this);
		autoReloadMenuItem.setChecked(true);
		settingMenu.add(autoReloadMenuItem);
		
		MenuItem reloadMenuItem = new MenuItem("Muat Ulang Data");
		reloadMenuItem.setId(RELOAD_TRIGGER_ID);
		reloadMenuItem.addSelectionHandler(this);
		settingMenu.add(reloadMenuItem);
		
		settingButton.setMenu(settingMenu);
		
		toolBar.add(createButton);
		toolBar.add(resendButton);
		toolBar.add(settingButton);
		
		return toolBar;
	}
	
	private void configureDataGrid() {
		dataStore = new ListStore<OutboundMessageProxy>(propertyAccess.key());
		
		ColumnConfig<OutboundMessageProxy, String> contentColumn = new ColumnConfig<OutboundMessageProxy, String>(propertyAccess.content(), 100, "Isi Pesan");
		ColumnConfig<OutboundMessageProxy, String> recipientColumn = new ColumnConfig<OutboundMessageProxy, String>(propertyAccess.recipient(), 50, "Tujuan");
		ColumnConfig<OutboundMessageProxy, Integer> statusColumn = new ColumnConfig<OutboundMessageProxy, Integer>(propertyAccess.status(), 50, "Status Pesan");
		
		selectionModel = new CheckBoxSelectionModel<OutboundMessageProxy>(new IdentityValueProvider<OutboundMessageProxy>());
		
		List<ColumnConfig<OutboundMessageProxy, ?>> columnsConfig = new ArrayList<ColumnConfig<OutboundMessageProxy,?>>();
		columnsConfig.add(selectionModel.getColumn());
		columnsConfig.add(recipientColumn);
		columnsConfig.add(contentColumn);
		columnsConfig.add(statusColumn);
		
		ColumnModel<OutboundMessageProxy> columnModel = new ColumnModel<OutboundMessageProxy>(columnsConfig);
		
		dataGrid = new Grid<OutboundMessageProxy>(dataStore, columnModel);
		dataGrid.setSelectionModel(selectionModel);
		dataGrid.setLoadMask(true);
		dataGrid.getView().setAutoExpandColumn(contentColumn);
		dataGrid.getView().setAutoFill(true);
		dataGrid.getView().setEmptyText("Data pesan keluar tidak ditemukan dalam basis data system.");
	}
}
