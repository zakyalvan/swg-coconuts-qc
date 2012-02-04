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
import com.sencha.gxt.widget.core.client.button.SplitButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.web.client.presenter.impl.InboundMessagePresenter.InboundMessageView;
import com.swg.web.shared.proxy.InboundMessageProxy;

public class InboundMessageWidget implements InboundMessageView, SelectHandler, SelectionHandler<Item> {
	interface InboundMessageProxyProperty extends PropertyAccess<InboundMessageProxy> {
		@Path("id")
		ModelKeyProvider<InboundMessageProxy> key();
		ValueProvider<InboundMessageProxy, Integer> id();
		ValueProvider<InboundMessageProxy, String> sender();
		ValueProvider<InboundMessageProxy, String> serviceCenter();
		ValueProvider<InboundMessageProxy, String> content();
		ValueProvider<InboundMessageProxy, Date> receiveDate();
		ValueProvider<InboundMessageProxy, Integer> status();
		ValueProvider<InboundMessageProxy, Date> version();
	}

	private InboundMessageProxyProperty proxyProperties = GWT.create(InboundMessageProxyProperty.class);
	
	private Logger logger = Logger.getLogger("InboundMessageWidget");
	
	private EventBus eventBus;
	private VerticalLayoutContainer container;
	
	private ListStore<InboundMessageProxy> dataStore;
	private CheckBoxSelectionModel<InboundMessageProxy> selectionModel;
	private Grid<InboundMessageProxy> dataGrid;
	
	private boolean autoreloadEnabled = true;
	private boolean autoreloadPartial = true;
	private Date lastDataVersion;
	
	@Inject
	public InboundMessageWidget(EventBus eventBus) {
		this.eventBus = eventBus;
		
		container = new VerticalLayoutContainer();
		
		ToolBar toolBar = createToolBar();
		container.add(toolBar);
		
		configureDataGrid();
		container.add(dataGrid);
	}
	@Override
	public void configureTab(TabItemConfig config) {
		logger.info("Configure tab presentation.");
		
		config.setClosable(true);
		config.setText("Pesan Masuk");
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
	public void setDatas(List<InboundMessageProxy> datas) {
		setDatas(datas, false);
	}
	@Override
	public void setDatas(List<InboundMessageProxy> datas, boolean partial) {
		if(partial) {
			List<InboundMessageProxy> storeDatas = dataStore.getAll();
			/**
			 * TODO Perbaiki teknik looping untuk remove data ini.
			 */
			for(InboundMessageProxy data : datas) {
				for(InboundMessageProxy storeData : storeDatas) {
					if(data.getId() == storeData.getId()) {
						dataStore.remove(storeData);
					}
				}
			}
		}
		else {
			dataStore.clear();
		}
		
		for(InboundMessageProxy data : datas) {
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
	/**
	 *  Handler method untuk button event.
	 */
	@Override
	public void onSelect(SelectEvent event) {
		
	}

	/**
	 *  Handler method untuk menu/menu item event.
	 */
	@Override
	public void onSelection(SelectionEvent<Item> event) {
		
	}
	
	private void configureDataGrid() {
		dataStore = new ListStore<InboundMessageProxy>(proxyProperties.key());
		
		selectionModel = new CheckBoxSelectionModel<InboundMessageProxy>(new IdentityValueProvider<InboundMessageProxy>());
		
		ColumnConfig<InboundMessageProxy, String> contentColumn = new ColumnConfig<InboundMessageProxy, String>(proxyProperties.content(), 150, "Isi Pesan");
		ColumnConfig<InboundMessageProxy, String> senderColumn = new ColumnConfig<InboundMessageProxy, String>(proxyProperties.sender(), 50, "Pengirim");
		ColumnConfig<InboundMessageProxy, Date> receiveDateColumn = new ColumnConfig<InboundMessageProxy, Date>(proxyProperties.receiveDate(), 50, "Waktu Masuk");
		ColumnConfig<InboundMessageProxy, Integer> statusColumn = new ColumnConfig<InboundMessageProxy, Integer>(proxyProperties.status(), 50, "Status");
		
		List<ColumnConfig<InboundMessageProxy, ?>> columnList = new ArrayList<ColumnConfig<InboundMessageProxy, ?>>();
		columnList.add(selectionModel.getColumn());
		columnList.add(senderColumn);
		columnList.add(contentColumn);
		columnList.add(receiveDateColumn);
		columnList.add(statusColumn);
		
		ColumnModel<InboundMessageProxy> columnModel = new ColumnModel<InboundMessageProxy>(columnList);
		
		dataGrid = new Grid<InboundMessageProxy>(dataStore, columnModel);
		dataGrid.setLoadMask(true);
		dataGrid.setSelectionModel(selectionModel);
		dataGrid.getView().setAutoExpandColumn(contentColumn);
		dataGrid.getView().setAutoFill(true);
		dataGrid.getView().setEmptyText("Data pesan masuk tidak ditemukan dalam basis data aplikasi ini");
	}
	
	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		
		TextButton reprocessButton = new TextButton("Proses Ulang");
		reprocessButton.setId(REPROCESS_BUTTON_ID);
		reprocessButton.addSelectHandler(this);
		toolBar.add(reprocessButton);
		
		TextButton settingButton = new TextButton("Pengaturan");
		Menu settingMenu = new Menu();
		
		MenuItem filteringStatusMenuItem = new MenuItem("Filter Status Pesan");
		settingMenu.add(filteringStatusMenuItem);
		
		Menu filterStatusSubMenu = new Menu();
		
		CheckMenuItem statusNewMenuItem = new CheckMenuItem("Baru");
		statusNewMenuItem.setChecked(true);
		filterStatusSubMenu.add(statusNewMenuItem);
		
		CheckMenuItem statusProcessedMenuItem = new CheckMenuItem("Dalam Proses");
		statusProcessedMenuItem.setChecked(true);
		filterStatusSubMenu.add(statusProcessedMenuItem);
		
		CheckMenuItem statusFailedMenuItem = new CheckMenuItem("Gagal Diproses");
		statusFailedMenuItem.setChecked(true);
		filterStatusSubMenu.add(statusFailedMenuItem);
		
		CheckMenuItem statusInvalidMenuItem = new CheckMenuItem("Tidak Syah");
		statusInvalidMenuItem.setChecked(true);
		filterStatusSubMenu.add(statusInvalidMenuItem);
		
		filteringStatusMenuItem.setSubMenu(filterStatusSubMenu);
		
		CheckMenuItem groupingStatusMenuItem = new CheckMenuItem("Kelompokan Status Pesan");
		settingMenu.add(groupingStatusMenuItem);
		
		settingMenu.add(new SeparatorMenuItem());
		
		CheckMenuItem autoReloadMenuItem = new CheckMenuItem("Muat Ulang Automatis");
		autoReloadMenuItem.setChecked(true);
		settingMenu.add(autoReloadMenuItem);
		
		MenuItem reloadMenuItem = new MenuItem("Muat Ulang Data");
		settingMenu.add(reloadMenuItem);
		
		settingButton.setMenu(settingMenu);
		
		toolBar.add(settingButton);
		
		toolBar.add(new FillToolItem());
		
		TextField searchTextBox = new TextField();
		searchTextBox.setEmptyText("Pencarian");
		toolBar.add(searchTextBox);
		
		SplitButton searchButton = new SplitButton("Pencarian");
		Menu searchMenu = new Menu();
		
		MenuItem complexSearchMenuItem = new MenuItem("Pencarian Lanjut");
		searchMenu.add(complexSearchMenuItem);
		
		searchButton.setMenu(searchMenu);
		toolBar.add(searchButton);
		
		return toolBar;
	}
}
