package com.swg.client.view.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.data.shared.loader.RequestFactoryProxy;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.client.presenter.VoteObserverPresenter.View;
import com.swg.shared.proxy.VoteObserverPagedListProxy;
import com.swg.shared.proxy.VoteObserverProxy;
import com.swg.shared.request.VoteObserverRequestFactory;
import com.swg.shared.request.VoteObserverRequestFactory.ManagementRequest;

/**
 * Kelas ini untuk nampilin grid yang berisi data pemantau atau saksi
 * untuk masing masing-desa atau kelurahan dan utilitas lainnya yang berkaitan 
 * dengan management data pemantau.
 * 
 * @author zakyalvan
 */
public class VoteObserverWidget implements View, SelectHandler, SelectionHandler<Item> {
	interface ObserverProxyProperty extends PropertyAccess<VoteObserverProxy> {
		ModelKeyProvider<VoteObserverProxy> id();
		ValueProvider<VoteObserverProxy, String> fullName();
		ValueProvider<VoteObserverProxy, String> cellularNumber();
	}
	
	private final Logger logger = Logger.getLogger("ObserverListWidget");
	
	private static final String DELETE_BUTTON_ID = "delete-button";
	private static final String ADD_BUTTON_ID = "add-button";
	private static final String UPLOAD_BUTTON_ID = "upload-button";
	
	private EventBus eventBus;
	private VoteObserverRequestFactory requestFactory;
	
	private ToolBar controlToolBar;
	private RequestFactoryProxy<PagingLoadConfig, PagingLoadResult<VoteObserverProxy>> requestProxy;
	private PagingLoader<PagingLoadConfig, PagingLoadResult<VoteObserverProxy>> requestLoader;
	private ListStore<VoteObserverProxy> dataStore;
	private Grid<VoteObserverProxy> dataGrid;
	private PagingToolBar pagingToolBar;
	
	private VerticalLayoutContainer container;
	
	private boolean configured = false;
	
	@Inject(optional=false)
	public VoteObserverWidget(EventBus eventBus) {
		logger.setLevel(Level.FINEST);
		this.eventBus = eventBus;
	}
	
	public VoteObserverWidget(EventBus eventBus, VoteObserverRequestFactory requestFactory) {
		this.eventBus = eventBus;
		this.requestFactory = requestFactory;
		
		logger.setLevel(Level.FINEST);
	}

	protected void configure() {
		if(LogConfiguration.loggingIsEnabled())
			logger.info("Configure widget.");
		
		ObserverProxyProperty observerProperties = GWT.create(ObserverProxyProperty.class);
		
		/**
		 * Create and configure control toolBar
		 */
		controlToolBar = new ToolBar();
		
		TextButton addButton = new TextButton("Tambah Data");
		Menu addMenu = new Menu();
		MenuItem formMenuItem = new MenuItem("Form Data Pemantau");
		formMenuItem.setId(ADD_BUTTON_ID);
		addMenu.add(formMenuItem);
		MenuItem uploadMenuItem = new MenuItem("Upload Data Pemantau");
		uploadMenuItem.setId(UPLOAD_BUTTON_ID);
		uploadMenuItem.addSelectionHandler(this);
		addMenu.add(uploadMenuItem);
		addButton.setMenu(addMenu);
		
		final TextButton deleteButton = new TextButton("Hapus Pilihan");
		deleteButton.setId(DELETE_BUTTON_ID);
		deleteButton.addSelectHandler(this);
		deleteButton.disable();
		
		controlToolBar.add(addButton);
		controlToolBar.add(new SeparatorToolItem());
		controlToolBar.add(deleteButton);
		
		/**
		 * Configure request proxy, request loader and data store.
		 */
		requestProxy = new RequestFactoryProxy<PagingLoadConfig, PagingLoadResult<VoteObserverProxy>>() {
			@Override
			public void load(PagingLoadConfig loadConfig, final Receiver<? super PagingLoadResult<VoteObserverProxy>> receiver) {
				ManagementRequest request = requestFactory.getManagementRequest();
				request.listVoteObservers(loadConfig.getOffset(), loadConfig.getLimit())
					.to(new Receiver<VoteObserverPagedListProxy>() {
						@Override
						public void onSuccess(VoteObserverPagedListProxy response) {
							PagingLoadResultBean<VoteObserverProxy> pagingLoadResult = new PagingLoadResultBean<VoteObserverProxy>();
							
							pagingLoadResult.setData(response.getDatas());
							pagingLoadResult.setOffset(response.getOffset());
							pagingLoadResult.setTotalLength((int) ((long)response.getTotal()));
							
							receiver.onSuccess(pagingLoadResult);
						}
						@Override
						public void onFailure(ServerFailure error) {
							super.onFailure(error);
							receiver.onFailure(error);
						}
					})
					.fire();
			}
		};
		requestLoader = new PagingLoader<PagingLoadConfig, PagingLoadResult<VoteObserverProxy>>(requestProxy);
		
		dataStore = new ListStore<VoteObserverProxy>(observerProperties.id());
		requestLoader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, VoteObserverProxy, PagingLoadResult<VoteObserverProxy>>(dataStore));
		
		/**
		 * Configure grid column and their appearance.
		 */
		CheckBoxSelectionModel<VoteObserverProxy> selectionModel = new CheckBoxSelectionModel<VoteObserverProxy>(new IdentityValueProvider<VoteObserverProxy>());
		selectionModel.setSelectionMode(SelectionMode.MULTI);
		selectionModel.addSelectionChangedHandler(new SelectionChangedHandler<VoteObserverProxy>() {
			@Override
			public void onSelectionChanged(SelectionChangedEvent<VoteObserverProxy> event) {
				if(event.getSelection().size() > 0) {
					deleteButton.enable();
				}
				else {
					deleteButton.disable();
				}
			}
		});
		
		ColumnConfig<VoteObserverProxy, String> nameColumn = new ColumnConfig<VoteObserverProxy, String>(observerProperties.fullName(), 150, "Nama Pemantau");
		ColumnConfig<VoteObserverProxy, String> phoneColumn = new ColumnConfig<VoteObserverProxy, String>(observerProperties.cellularNumber(), 100, "No Telepon");
		List<ColumnConfig<VoteObserverProxy, ?>> columnList = new ArrayList<ColumnConfig<VoteObserverProxy,?>>();
		columnList.add(selectionModel.getColumn());
		columnList.add(nameColumn);
		columnList.add(phoneColumn);
		
		ColumnModel<VoteObserverProxy> columnModel = new ColumnModel<VoteObserverProxy>(columnList);
		
		/**
		 * Create and configure grid object.
		 */
		dataGrid = new Grid<VoteObserverProxy>(dataStore, columnModel) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				/**
				 * Pada saat pertama kali di attach, bikin scheduler buat ngeload data pertama kali.
				 */
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						requestLoader.load();
					}
				});
			}
		};
		dataGrid.setSelectionModel(selectionModel);
		dataGrid.getView().setForceFit(true);
		dataGrid.setLoadMask(true);
		dataGrid.setLoader(requestLoader);
		
		/**
		 * Create and configure paging toolbar.
		 */
		pagingToolBar = new PagingToolBar(50);
		pagingToolBar.bind(requestLoader);
		
		container = new VerticalLayoutContainer();
		container.add(controlToolBar);
		container.add(dataGrid);
		container.add(pagingToolBar);
		
		configured = true;
	}
		
	@Override
	public Widget asWidget() {
		if(!configured)
			configure();
		return container;
	}

	@Override
	public void onSelect(SelectEvent event) {
		if(event.getSource() instanceof TextButton) {
			TextButton sourceButton = (TextButton) event.getSource();
			if(sourceButton.getId().equals(DELETE_BUTTON_ID)) {
				final ConfirmMessageBox confirmBox = new ConfirmMessageBox("Konfirmasi", "Anda setuju untuk menghapus data yang dipilih?");
				confirmBox.addHideHandler(new HideHandler() {
					@Override
					public void onHide(HideEvent event) {
						dataGrid.mask("Menghapus data, mohon tunggu...");
						final List<VoteObserverProxy> willBeDeleted = dataGrid.getSelectionModel().getSelectedItems();
						
						ManagementRequest request = requestFactory.getManagementRequest();
						request.delete(willBeDeleted)
							.to(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									dataGrid.unmask();
									// Setelah data berhasil dihapus diserver, hapus data di store.
									for(VoteObserverProxy deleted : willBeDeleted) {
										dataStore.remove(deleted);
									}
								}
								@Override
								public void onFailure(ServerFailure error) {
									super.onFailure(error);
									dataGrid.unmask();
								}
							})
							.fire();
					}
				});
				confirmBox.show();
			}
		}
	}

	@Override
	public void onSelection(SelectionEvent<Item> event) {
		
	}

	@Override
	public void configureTab(TabItemConfig config) {
		config.setText("Data Pemantau");
		config.setClosable(false);
	}
}
