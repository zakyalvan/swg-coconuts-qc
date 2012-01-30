package com.swg.web.client.view.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.web.client.event.AddGatewayRequestEvent;
import com.swg.web.client.event.RemoveGatewayRequestEvent;
import com.swg.web.client.event.SmsServiceStartReqEvent;
import com.swg.web.client.event.SmsServiceStopReqEvent;
import com.swg.web.client.event.AddGatewayRequestEvent.SupportedGatewayType;
import com.swg.web.client.presenter.SmsServicePresenter;
import com.swg.web.client.presenter.SmsServicePresenter.View;
import com.swg.web.shared.proxy.GatewayInfoProxy;
import com.swg.web.shared.proxy.SerialGatewayInfoProxy;

/**
 * Widget untuk urusan management messaging (sms) service.
 * 
 * @author zakyalvan
 */
public class SmsServiceWidget implements SmsServicePresenter.View, SelectHandler, SelectionHandler<Item>, SelectionChangedHandler<GatewayInfoProxy> {
	interface GatewayInfoPropertyAccess extends PropertyAccess<GatewayInfoProxy> {
		@Path("id")
		ModelKeyProvider<GatewayInfoProxy> key();
		ValueProvider<GatewayInfoProxy, String> id();
		ValueProvider<GatewayInfoProxy, Integer> capability();
		ValueProvider<GatewayInfoProxy, Boolean> enabled();
		ValueProvider<GatewayInfoProxy, Boolean> activated();
	}
	
	private GatewayInfoPropertyAccess gatewayProperties = GWT.create(GatewayInfoPropertyAccess.class);
	
	private Logger logger = Logger.getLogger("SmsServiceMngmntWidget");
	
	private EventBus eventBus;
	private SerialGatewayEditorWidget editorWidget;
	
	private VerticalLayoutContainer container;
	private ListStore<GatewayInfoProxy> dataStore;
	private List<GatewayInfoProxy> selectedGateways = new ArrayList<GatewayInfoProxy>();
	
	private TextButton startServiceButton;
	private TextButton stopServiceButton;
	private TextButton addGatewayButton;
	private TextButton removeGatewayButton;
	private TextButton enableGatewayButton;
	private TextButton disableGatewayButton;
	
	@Inject(optional=false)
	public SmsServiceWidget(EventBus eventBus, SerialGatewayEditorWidget editorWidget) {
		logger.setLevel(Level.FINEST);
		
		this.eventBus = eventBus;
		this.editorWidget = editorWidget;
	}
	
	@Override
	public Widget asWidget() {
		logger.info("Configure messaging service widget.");
		
		container = new VerticalLayoutContainer();
		
		ToolBar toolBar = new ToolBar();
		
		startServiceButton = new TextButton("Start Service");
		startServiceButton.setId(START_SRVC_BTN_ID);
		startServiceButton.addSelectHandler(this);
		toolBar.add(startServiceButton);
		
		stopServiceButton = new TextButton("Stop Service");
		stopServiceButton.setId(STOP_SRVC_BTN_ID);
		stopServiceButton.addSelectHandler(this);
		toolBar.add(stopServiceButton);
		
		toolBar.add(new SeparatorToolItem());
		
		addGatewayButton = new TextButton("Tambah Gateway");
		Menu addGatewayMenu = new Menu();
		MenuItem serialModelMenuItem = new MenuItem("Gateway Modem Serial");
		serialModelMenuItem.setId(ADD_SERIAL_GTW_BTN_ID);
		serialModelMenuItem.addSelectionHandler(this);
		addGatewayMenu.add(serialModelMenuItem);
		addGatewayButton.setMenu(addGatewayMenu);
		toolBar.add(addGatewayButton);
		
		removeGatewayButton = new TextButton("Hapus Gateway");
		removeGatewayButton.setId(REMOVE_GTW_BTN_ID);
		removeGatewayButton.addSelectHandler(this);
		toolBar.add(removeGatewayButton);
		
		enableGatewayButton = new TextButton("Enable Gateway");
		enableGatewayButton.setId(ENABLE_GTW_BTN_ID);
		enableGatewayButton.addSelectHandler(this);
		toolBar.add(enableGatewayButton);
		
		disableGatewayButton = new TextButton("Disable Gateway");
		disableGatewayButton.setId(DISABLE_GTW_BTN_ID);
		disableGatewayButton.addSelectHandler(this);
		toolBar.add(disableGatewayButton);
		
		container.add(toolBar);
		
		dataStore = new ListStore<GatewayInfoProxy>(gatewayProperties.key());
		
		CheckBoxSelectionModel<GatewayInfoProxy> selectionModel = new CheckBoxSelectionModel<GatewayInfoProxy>(new IdentityValueProvider<GatewayInfoProxy>());
		selectionModel.addSelectionChangedHandler(this);
		
		ColumnConfig<GatewayInfoProxy, String> idColumn = new ColumnConfig<GatewayInfoProxy, String>(gatewayProperties.id(), 100, "Identifier");
		ColumnConfig<GatewayInfoProxy, Integer> capabilityColumn = new ColumnConfig<GatewayInfoProxy, Integer>(gatewayProperties.capability(), 50, "Capability");
		ColumnConfig<GatewayInfoProxy, Boolean> enabledColumn = new ColumnConfig<GatewayInfoProxy, Boolean>(gatewayProperties.enabled(), 50, "Enabled");
		ColumnConfig<GatewayInfoProxy, Boolean> activatedColumn = new ColumnConfig<GatewayInfoProxy, Boolean>(gatewayProperties.activated(), 50, "Activated");
		
		List<ColumnConfig<GatewayInfoProxy, ?>> columnList = new ArrayList<ColumnConfig<GatewayInfoProxy,?>>();
		columnList.add(selectionModel.getColumn());
		columnList.add(idColumn);
		columnList.add(capabilityColumn);
		columnList.add(enabledColumn);
		columnList.add(activatedColumn);
		
		ColumnModel<GatewayInfoProxy> columnModel = new ColumnModel<GatewayInfoProxy>(columnList);
		
		Grid<GatewayInfoProxy> dataGrid = new Grid<GatewayInfoProxy>(dataStore, columnModel);
		dataGrid.setSelectionModel(selectionModel);
		dataGrid.getView().setEmptyText("Tidak ada informasi gateway yang ditemukan");
		dataGrid.getView().setAutoExpandColumn(idColumn);
		dataGrid.getView().setForceFit(true);
		
		container.add(dataGrid);
		
		return container;
	}

	@Override
	public void configureTab(TabItemConfig config) {
		config.setText("Server Pesan Singkat");
		config.setClosable(true);
	}

	
	
	@Override
	public void notifyServiceStarted() {
		logger.info("Notifikasi service distart.");
		startServiceButton.disable();
		stopServiceButton.enable();
	}
	@Override
	public void notifyServiceStartingFailed(Throwable failureCause) {
		logger.info("Notifikasi starting service gagal.");
		startServiceButton.enable();
		stopServiceButton.disable();
	}
	@Override
	public void notifyServiceStoped() {
		logger.info("Notifikasi service distop.");
		startServiceButton.enable();
		stopServiceButton.disable();
	}
	@Override
	public void notifyServiceStopingFailed(Throwable failureCause) {
		logger.info("Notifikasi stoping service gagal.");
		startServiceButton.disable();
		stopServiceButton.enable();
	}

	@Override
	public void startCreateGateway(GatewayInfoProxy gatewayInfoProxy, RequestContext requestContext) {
		logger.info("Start create gateway.");
		Window window = new Window();
		
		SerialGatewayEditorWidget editor = new SerialGatewayEditorWidget(eventBus);
		editor.configureWindow(window);
		window.add(editor);
		
		editor.startEditor((SerialGatewayInfoProxy) gatewayInfoProxy, requestContext);
		
		window.setVisible(true);
	}

	@Override
	public void startUpdateGateway(GatewayInfoProxy gatewayInfoProxy) {
		
	}

	@Override
	public void setGatewayInfos(List<GatewayInfoProxy> gatewayInfos) {
		
	}

	@Override
	public void notifyRemovedGateways(List<GatewayInfoProxy> gatewayInfos) {
		
	}

	/**
	 * Handler perubahan pilihan user pada grid.
	 * 
	 * @param event
	 */
	@Override
	public void onSelectionChanged(SelectionChangedEvent<GatewayInfoProxy> event) {
		selectedGateways.clear();
		selectedGateways.addAll(event.getSelection());
		
		boolean hasEnabledGateway = false;
		boolean hasDisabledGateway = false;
		for(GatewayInfoProxy gateway : selectedGateways) {
			if(gateway.isEnabled())
				hasEnabledGateway = true;
			if(!gateway.isEnabled())
				hasDisabledGateway = true;
		}
		
		if(hasEnabledGateway)
			disableGatewayButton.enable();
		else
			disableGatewayButton.disable();
		
		if(hasDisabledGateway)
			enableGatewayButton.enable();
		else
			enableGatewayButton.disable();
	}

	/**
	 * Handler pada selection (click) user pada button-button dalam widget ini.
	 * 
	 * @param {@link SelectEvent} event
	 */
	@Override
	public void onSelect(SelectEvent event) {
		if(event.getSource() instanceof TextButton) {
			TextButton sourceButton = (TextButton) event.getSource();
			
			if(sourceButton.getId().equals(View.START_SRVC_BTN_ID)) {
				sourceButton.disable();
				eventBus.fireEvent(new SmsServiceStartReqEvent());
			}
			else if(sourceButton.getId().equals(View.STOP_SRVC_BTN_ID)) {
				sourceButton.disable();
				eventBus.fireEvent(new SmsServiceStopReqEvent());
			}
			
			else if(sourceButton.getId().equals(REMOVE_GTW_BTN_ID)) {
				eventBus.fireEvent(new RemoveGatewayRequestEvent(selectedGateways));
			}
			else if(sourceButton.getId().equals(UPDATE_GTW_BTN_ID)) {
				
			}
			else if(sourceButton.getId().equals(ENABLE_GTW_BTN_ID)) {
				Info.display("Info", "Enable gateway here.");
			}
			else if(sourceButton.getId().equals(DISABLE_GTW_BTN_ID)) {
				Info.display("Info", "Disable gateway here.");
			}
		}
	}

	@Override
	public void onSelection(SelectionEvent<Item> event) {
		eventBus.fireEvent(new AddGatewayRequestEvent(SupportedGatewayType.SERIAL_MODEM));
	}
}
