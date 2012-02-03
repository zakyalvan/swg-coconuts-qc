package com.swg.web.client.view.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
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
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.web.client.presenter.InboundMessagePresenter.View;
import com.swg.web.shared.proxy.InboundMessageProxy;

public class InboundMessageWidget implements View {
	interface InboundMessageProxyProperty extends PropertyAccess<InboundMessageProxy> {
		@Path("id")
		ModelKeyProvider<InboundMessageProxy> key();
		ValueProvider<InboundMessageProxy, Integer> id();
		ValueProvider<InboundMessageProxy, String> sender();
		ValueProvider<InboundMessageProxy, String> serviceCenter();
		ValueProvider<InboundMessageProxy, String> content();
		ValueProvider<InboundMessageProxy, Date> receiveDate();
	}
	
	private InboundMessageProxyProperty proxyProperties = GWT.create(InboundMessageProxyProperty.class);
	
	private Logger logger = Logger.getLogger("InboundMessageWidget");
	
	private EventBus eventBus;
	private SimpleContainer container;
	
	@Inject
	public InboundMessageWidget(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public void configureTab(TabItemConfig config) {
		logger.info("Configure tab presentation.");
		
		config.setClosable(true);
		config.setText("Pesan Masuk");
	}

	@Override
	public Widget asWidget() {
		container = new SimpleContainer();
		
		VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();
		
		ToolBar toolBar = new ToolBar();
		
		toolBar.add(new FillToolItem());
		
		TextField searchTextBox = new TextField();
		searchTextBox.setEmptyText("Pencarian");
		
		SplitButton searchButton = new SplitButton("Pencarian");
		Menu searchMenu = new Menu();
		
		MenuItem complexSearchMenuItem = new MenuItem("Pencarian Lanjut");
		searchMenu.add(complexSearchMenuItem);
		
		searchButton.setMenu(searchMenu);
		
		layoutContainer.add(toolBar);
		
		ListStore<InboundMessageProxy> dataStore = new ListStore<InboundMessageProxy>(proxyProperties.key());
		
		CheckBoxSelectionModel<InboundMessageProxy> selectionModel = new CheckBoxSelectionModel<InboundMessageProxy>(new IdentityValueProvider<InboundMessageProxy>());
		
		ColumnConfig<InboundMessageProxy, String> contentColumn = new ColumnConfig<InboundMessageProxy, String>(proxyProperties.content(), 100, "Isi");
		ColumnConfig<InboundMessageProxy, String> senderColumn = new ColumnConfig<InboundMessageProxy, String>(proxyProperties.sender());
		
		List<ColumnConfig<InboundMessageProxy, ?>> columnList = new ArrayList<ColumnConfig<InboundMessageProxy, ?>>();
		columnList.add(selectionModel.getColumn());
		columnList.add(senderColumn);
		columnList.add(contentColumn);
		
		ColumnModel<InboundMessageProxy> columnModel = new ColumnModel<InboundMessageProxy>(columnList);
		
		Grid<InboundMessageProxy> dataGrid = new Grid<InboundMessageProxy>(dataStore, columnModel);
		dataGrid.setLoadMask(true);
		dataGrid.setSelectionModel(selectionModel);
		dataGrid.getView().setAutoExpandColumn(contentColumn);
		dataGrid.getView().setAutoFill(true);
		
		layoutContainer.add(dataGrid);
		
		container.add(layoutContainer);
		return container;
	}

}
