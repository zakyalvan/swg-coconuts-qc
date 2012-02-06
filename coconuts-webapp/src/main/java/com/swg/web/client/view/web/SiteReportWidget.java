package com.swg.web.client.view.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
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
import com.swg.web.client.presenter.impl.SiteReportPresenter.SiteReportView;
import com.swg.web.client.view.DataContainer;
import com.swg.web.shared.proxy.SiteReportProxy;

/**
 * Widget yang nampilin data laporan dari lapangan pada saat proses pemungutan suara.
 * 
 * @author zakyalvan
 */
public class SiteReportWidget implements SiteReportView, DataContainer<SiteReportProxy, Date>, SelectHandler, SelectionHandler<Item> {
	interface SiteReportProxyPropertyAccess extends PropertyAccess<SiteReportProxy> {
		@Path("id")
		ModelKeyProvider<SiteReportProxy> key();
		ValueProvider<SiteReportProxy, Integer> id();
		ValueProvider<SiteReportProxy, String> content();
		ValueProvider<SiteReportProxy, Integer> priority();
		ValueProvider<SiteReportProxy, Date> reportDate();
		ValueProvider<SiteReportProxy, Date> version();
	}
	
	private EventBus eventBus;
	
	private VerticalLayoutContainer container;
	
	private boolean autoreloadEnabled = true;
	private boolean autoreloadPartial = true;
	
	private Date lastDataVersion;
	
	private ListStore<SiteReportProxy> dataStore;
	private CheckBoxSelectionModel<SiteReportProxy> selectionModel;
	private Grid<SiteReportProxy> dataGrid;
	
	private SiteReportProxyPropertyAccess propertyAccess = GWT.create(SiteReportProxyPropertyAccess.class);
	
	@Inject
	public SiteReportWidget(EventBus eventBus) {
		this.eventBus = eventBus;
		
		container = new VerticalLayoutContainer();
		
		ToolBar toolBar = createToolBar();
		container.add(toolBar, new VerticalLayoutData(1, -1));
		
		configureDataGrid();
		container.add(dataGrid, new VerticalLayoutData(1, 1));
	}
	
	@Override
	public void configureTab(TabItemConfig config) {
		config.setText("Laporan Lapangan");
		config.setClosable(true);
	}

	@Override
	public Widget asWidget() {
		return container;
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
	public void setDatas(List<SiteReportProxy> datas) {
		setDatas(datas, false);
	}

	@Override
	public void setDatas(List<SiteReportProxy> datas, boolean partial) {
		if(partial) {
			List<SiteReportProxy> storeDatas = dataStore.getAll();
			for(SiteReportProxy data : datas) {
				for(SiteReportProxy storeData : storeDatas) {
					if(data.getId() == storeData.getId()) {
						dataStore.remove(storeData);
					}
				}
			}
		}
		else {
			dataStore.clear();
		}
		
		for(SiteReportProxy data : datas) {
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
		dataStore.clear();
	}

	@Override
	public void onSelection(SelectionEvent<Item> event) {
		if(event.getSelectedItem().getId().equals(AUTORELOAD_TRIGGER_ID)) {
			autoreloadEnabled = ((CheckMenuItem) event.getSelectedItem()).isChecked();
		}
		else if(event.getSelectedItem().getId().equals(RELOADPARTIAL_TRIGGER_ID)) {
			autoreloadPartial = ((CheckMenuItem) event.getSelectedItem()).isChecked();
		}
		else if(event.getSelectedItem().getId().equals(AUTORELOAD_TRIGGER_ID)) {
		}
	}
	@Override
	public void onSelect(SelectEvent event) {
		
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		TextButton deleteButton = new TextButton("Hapus Laporan");
		deleteButton.setId(DELETE_TRIGGER_ID);
		deleteButton.addSelectHandler(this);
		
		TextButton settingButton = new TextButton("Pengaturan");
		Menu settingMenu = new Menu();
		
		settingMenu.add(new SeparatorMenuItem());
		
		CheckMenuItem autoReloadMenuItem = new CheckMenuItem("Muat Ulang Automatis");
		autoReloadMenuItem.setId(AUTORELOAD_TRIGGER_ID);
		autoReloadMenuItem.addSelectionHandler(this);
		autoReloadMenuItem.setChecked(autoreloadPartial);
		settingMenu.add(autoReloadMenuItem);
		
		CheckMenuItem partialReloadMenuItem = new CheckMenuItem("Muat Ulang Parsial");
		partialReloadMenuItem.setId(RELOADPARTIAL_TRIGGER_ID);
		partialReloadMenuItem.addSelectionHandler(this);
		partialReloadMenuItem.setChecked(autoreloadPartial);
		settingMenu.add(partialReloadMenuItem);
		
		MenuItem reloadMenuItem = new MenuItem("Muat Ulang Data");
		reloadMenuItem.setId(RELOAD_TRIGGER_ID);
		reloadMenuItem.addSelectionHandler(this);
		settingMenu.add(reloadMenuItem);
		
		settingButton.setMenu(settingMenu);
		
		toolBar.add(deleteButton);
		toolBar.add(settingButton);
		return toolBar;
	}
	private void configureDataGrid() {
		dataStore = new ListStore<SiteReportProxy>(propertyAccess.key());
		
		selectionModel = new CheckBoxSelectionModel<SiteReportProxy>(new IdentityValueProvider<SiteReportProxy>());
		
		ColumnConfig<SiteReportProxy, String> contentColumn = new ColumnConfig<SiteReportProxy, String>(propertyAccess.content(), 100, "Isi Laporan");
		ColumnConfig<SiteReportProxy, Integer> priorityColumn = new ColumnConfig<SiteReportProxy, Integer>(propertyAccess.priority(), 30, "Prioritas");
		ColumnConfig<SiteReportProxy, Date> reportDateColumn = new ColumnConfig<SiteReportProxy, Date>(propertyAccess.reportDate(), 30, "Waktu Diterima");
		
		List<ColumnConfig<SiteReportProxy, ?>> columnList = new ArrayList<ColumnConfig<SiteReportProxy,?>>();
		columnList.add(selectionModel.getColumn());
		columnList.add(contentColumn);
		columnList.add(priorityColumn);
		columnList.add(reportDateColumn);
		
		ColumnModel<SiteReportProxy> columModel = new ColumnModel<SiteReportProxy>(columnList);
		
		dataGrid = new Grid<SiteReportProxy>(dataStore, columModel);
		dataGrid.setSelectionModel(selectionModel);
		dataGrid.getView().setAutoExpandColumn(contentColumn);
		dataGrid.getView().setForceFit(true);
		dataGrid.getView().setAutoFill(true);
		dataGrid.getView().setEmptyText("Data laporan lapangan tidak ditemukan dalam basis data system.");
	}
}
