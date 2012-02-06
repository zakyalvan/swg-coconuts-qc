package com.swg.web.client.view.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
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
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.web.client.presenter.impl.VoteObserverPresenter.VoteObserverView;
import com.swg.web.shared.proxy.VoteObserverProxy;

/**
 * Kelas ini untuk nampilin grid yang berisi data pemantau atau saksi
 * untuk masing masing-desa atau kelurahan dan utilitas lainnya yang berkaitan 
 * dengan management data pemantau.
 * 
 * @author zakyalvan
 */
public class VoteObserverWidget implements VoteObserverView, SelectHandler, SelectionHandler<Item> {
	interface ObserverProxyProperty extends PropertyAccess<VoteObserverProxy> {
		@Path("id")
		ModelKeyProvider<VoteObserverProxy> key();
		ValueProvider<VoteObserverProxy, Integer> id();
		ValueProvider<VoteObserverProxy, String> fullName();
		ValueProvider<VoteObserverProxy, String> cellularNumber();
		ValueProvider<VoteObserverProxy, Date> registeredDate();
		ValueProvider<VoteObserverProxy, Boolean> verified();
		ValueProvider<VoteObserverProxy, Date> verifiedDate();
		ValueProvider<VoteObserverProxy, Date> version();
	}
	
	private EventBus eventBus;
	
	private ToolBar toolBar;
	private ListStore<VoteObserverProxy> dataStore;
	private Grid<VoteObserverProxy> dataGrid;
	
	private VerticalLayoutContainer container;
	
	private boolean widgetConfigured = false;
	
	private Date lastDataVersion;
	private boolean autoreloadEnabled = true;
	private boolean autoreloadPartial = true;
	
	private ObserverProxyProperty observerProperties = GWT.create(ObserverProxyProperty.class);
	
	@Inject(optional=false)
	public VoteObserverWidget(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	protected void configureWidget() {
		/**
		 * Create and configure toolBar
		 */
		toolBar = new ToolBar();
		
		TextButton addButton = new TextButton("Tambah Data");
		Menu addMenu = new Menu();
		MenuItem formMenuItem = new MenuItem("Form Data Pemantau");
		formMenuItem.setId(ADD_TRIGGER_ID);
		addMenu.add(formMenuItem);
		MenuItem uploadMenuItem = new MenuItem("Upload Data Pemantau");
		uploadMenuItem.setId(UPLOAD_TRIGGER_ID);
		uploadMenuItem.addSelectionHandler(this);
		addMenu.add(uploadMenuItem);
		addButton.setMenu(addMenu);
		
		final TextButton deleteButton = new TextButton("Hapus Pilihan");
		deleteButton.setId(DELETE_TRIGGER_ID);
		deleteButton.addSelectHandler(this);
		deleteButton.disable();
		
		toolBar.add(addButton);
		toolBar.add(new SeparatorToolItem());
		toolBar.add(deleteButton);
		
		
		dataStore = new ListStore<VoteObserverProxy>(observerProperties.key());
		
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
		
		ColumnConfig<VoteObserverProxy, String> nameColumn = new ColumnConfig<VoteObserverProxy, String>(observerProperties.fullName(), 75, "Nama Pemantau");
		ColumnConfig<VoteObserverProxy, String> phoneColumn = new ColumnConfig<VoteObserverProxy, String>(observerProperties.cellularNumber(), 30, "No Telepon");
		ColumnConfig<VoteObserverProxy, Date> registeredDateColumn = new ColumnConfig<VoteObserverProxy, Date>(observerProperties.registeredDate(), 30, "Waktu Registrasi");
		registeredDateColumn.setCell(new DateCell(DateTimeFormat.getFormat("dd MMM yyyy HH:mm:ss")));
		ColumnConfig<VoteObserverProxy, Boolean> verifiedColum = new ColumnConfig<VoteObserverProxy, Boolean>(observerProperties.verified(), 20, "Verifikasi");
		verifiedColum.setCell(new AbstractCell<Boolean>() {
			@Override
			public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
				String style = "style='color: " + (value ? "green" : "red") + "'";
				sb.appendHtmlConstant("<span " + style + "'>" + (value ? "Sudah" : "Belum") + "</span>");
			}
		});
		ColumnConfig<VoteObserverProxy, Date> verifiedDateColumn = new ColumnConfig<VoteObserverProxy, Date>(observerProperties.verifiedDate(), 30, "Waktu Verifikasi");
		verifiedDateColumn.setCell(new DateCell(DateTimeFormat.getFormat("dd MMM yyyy HH:mm:ss")));
		
		List<ColumnConfig<VoteObserverProxy, ?>> columnList = new ArrayList<ColumnConfig<VoteObserverProxy,?>>();
		
		columnList.add(selectionModel.getColumn());
		columnList.add(nameColumn);
		columnList.add(phoneColumn);
		columnList.add(registeredDateColumn);
		columnList.add(verifiedColum);
		columnList.add(verifiedDateColumn);
		
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
					
					}
				});
			}
		};
		dataGrid.setSelectionModel(selectionModel);
		dataGrid.getView().setForceFit(true);
		dataGrid.getView().setAutoFill(true);
		dataGrid.getView().setAutoExpandColumn(nameColumn);		
		
		container = new VerticalLayoutContainer();
		container.add(toolBar);
		container.add(dataGrid);
		
		widgetConfigured = true;
	}
		
	@Override
	public Widget asWidget() {
		if(!widgetConfigured)
			configureWidget();
		return container;
	}

	@Override
	public void onSelect(SelectEvent event) {
		if(event.getSource() instanceof TextButton) {
			TextButton sourceButton = (TextButton) event.getSource();
			if(sourceButton.getId().equals(DELETE_TRIGGER_ID)) {
				final ConfirmMessageBox confirmBox = new ConfirmMessageBox("Konfirmasi", "Anda setuju untuk menghapus data yang dipilih?");
				confirmBox.addHideHandler(new HideHandler() {
					@Override
					public void onHide(HideEvent event) {
						dataGrid.mask("Menghapus data, mohon tunggu...");
						final List<VoteObserverProxy> willBeDeleted = dataGrid.getSelectionModel().getSelectedItems();
						
//						ManagementRequest request = requestFactory.getManagementRequest();
//						request.delete(willBeDeleted)
//							.to(new Receiver<Void>() {
//								@Override
//								public void onSuccess(Void response) {
//									dataGrid.unmask();
//									// Setelah data berhasil dihapus diserver, hapus data di store.
//									for(VoteObserverProxy deleted : willBeDeleted) {
//										dataStore.remove(deleted);
//									}
//								}
//								@Override
//								public void onFailure(ServerFailure error) {
//									super.onFailure(error);
//									dataGrid.unmask();
//								}
//							})
//							.fire();
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
		config.setClosable(true);
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
	public void setDatas(List<VoteObserverProxy> datas) {
		setDatas(datas, false);
	}

	@Override
	public void setDatas(List<VoteObserverProxy> datas, boolean partial) {
		if(partial) {
			List<VoteObserverProxy> storeDatas = dataStore.getAll();

			for(VoteObserverProxy data : datas) {
				for(VoteObserverProxy storeData : storeDatas) {
					if(data.getId() == storeData.getId()) {
						dataStore.remove(storeData);
					}
				}
			}
		}
		else {
			dataStore.clear();
		}
		
		for(VoteObserverProxy data : datas) {
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
}
