package com.swg.client.view.widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.swg.client.activity.VoteCountingActivity;
import com.swg.client.event.VoteCountDeleteReqEvent;
import com.swg.client.event.VoteCountRequestEvent;
import com.swg.client.presenter.VoteCountingPresenter.View;
import com.swg.shared.proxy.VoteResultProxy;

/**
 * Widget yang nampilin data hasil perhitungan suara (representasi dari aktifitas {@link VoteCountingActivity}).
 * Data ditampilkan keseluruhan dalam bentuk grid.
 * 
 * @author zakyalvan
 */
public class VoteCountingWidget implements View {
	public interface VoteCountProxyProperties extends PropertyAccess<VoteResultProxy> {
		ModelKeyProvider<VoteResultProxy> id();
		ValueProvider<VoteResultProxy, Date> updateTime();
	}
	
	private Logger logger = Logger.getLogger("VoteCountingWidget");
	
	private EventBus eventBus;
	
	/**
	 * Tempat penampungan (cache) untuk record yang dipilih pada grid.
	 * Untuk memudahkan akses dari presenter.
	 */
	private List<VoteResultProxy> selectedRecords = new ArrayList<VoteResultProxy>();
	
	private SimpleContainer contentPanel;
	
	@Inject(optional=false)
	public VoteCountingWidget(EventBus eventBus) {
		this.eventBus = eventBus;	
		logger.setLevel(Level.FINEST);
	}
	
	@Override
	public void configureTab(TabItemConfig config) {
		logger.info("Configure tab.");
		config.setText("Perhitungan Suara");
		config.setClosable(true);
	}

	@Override
	public Widget asWidget() {
		logger.info("Configure widget.");
		
		VoteCountProxyProperties proxyProperties = GWT.create(VoteCountProxyProperties.class);
		ModelKeyProvider<? super VoteResultProxy> keyProvider = proxyProperties.id();
		
		contentPanel = new SimpleContainer();
//		contentPanel.setBorders(true);
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		container.setDeferHeight(true);
		
		ToolBar controlToolBar = new ToolBar();
		TextButton deleteButton = new TextButton("Hapus Pilihan");
		deleteButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				// Fire event untuk penghapusan data hasil perhitungan suara yang dipilih dari grid.
				eventBus.fireEvent(new VoteCountDeleteReqEvent(VoteCountingWidget.this, selectedRecords));
			}
		});
		controlToolBar.add(deleteButton);
		
		CheckBoxSelectionModel<VoteResultProxy> selectionModel = 
			new CheckBoxSelectionModel<VoteResultProxy>(new IdentityValueProvider<VoteResultProxy>());
		selectionModel.addSelectionChangedHandler(new SelectionChangedHandler<VoteResultProxy>() {
			@Override
			public void onSelectionChanged(SelectionChangedEvent<VoteResultProxy> event) {
				// Updata list record yang dipilih.
				selectedRecords.clear();
				selectedRecords.addAll(event.getSelection());
			}
		});
		
		ColumnConfig<VoteResultProxy, Date> updateTimeColumn = new ColumnConfig<VoteResultProxy, Date>(proxyProperties.updateTime(), 100, "Waktu Update");
		
		List<ColumnConfig<VoteResultProxy, ?>> columnList = new ArrayList<ColumnConfig<VoteResultProxy, ?>>();
		columnList.add(selectionModel.getColumn());
		columnList.add(updateTimeColumn);
		
		ColumnModel<VoteResultProxy> columnModel = new ColumnModel<VoteResultProxy>(columnList);
		
		DataProxy<ListLoadConfig, ListLoadResult<VoteResultProxy>> dataProxy = 
			new DataProxy<ListLoadConfig, ListLoadResult<VoteResultProxy>>() {
				@Override
				public void load(ListLoadConfig loadConfig, Callback<ListLoadResult<VoteResultProxy>, Throwable> callback) {
					VoteCountRequestEvent requestEvent = new VoteCountRequestEvent(VoteCountingWidget.this);
					eventBus.fireEvent(requestEvent);
					
					if(requestEvent.isDispatched()) {
						if(requestEvent.isSuccess()) {
							ListLoadResult<VoteResultProxy> loadResult = new ListLoadResultBean<VoteResultProxy>(requestEvent.getResultRecords());
							callback.onSuccess(loadResult);
						}
						else {
							throw new RuntimeException("Load data failed, an error occured.", requestEvent.getFailureCause());
						}
					}
					else {
						throw new RuntimeException("No handler for data request event.");
					}
				}
			};
		
		ListStore<VoteResultProxy> dataStore = new ListStore<VoteResultProxy>(keyProvider);
		final ListLoader<ListLoadConfig, ListLoadResult<VoteResultProxy>> dataLoader = new ListLoader<ListLoadConfig, ListLoadResult<VoteResultProxy>>(dataProxy);
		dataLoader.addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig, VoteResultProxy, ListLoadResult<VoteResultProxy>>(dataStore));
		
		Grid<VoteResultProxy> dataGrid = new Grid<VoteResultProxy>(dataStore, columnModel) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						dataLoader.load();
					}
				});
			}
		};
		dataGrid.setSelectionModel(selectionModel);
		dataGrid.getView().setForceFit(true);
		dataGrid.getView().setAutoFill(true);
		dataGrid.getView().setAutoExpandColumn(updateTimeColumn);
		dataGrid.setLoadMask(true);
		dataGrid.setLoader(dataLoader);
		
		container.add(controlToolBar, new VerticalLayoutData(1, -1));
		container.add(dataGrid, new VerticalLayoutData(1, 1));
		
		contentPanel.add(container);
		
		contentPanel.forceLayout();
		return contentPanel;
	}

	@Override
	public List<VoteResultProxy> getSelectedRecords() {
		return selectedRecords;
	}

	@Override
	public void appendDatas(List<VoteResultProxy> datas) {
		
	}

	@Override
	public void setDatas(List<VoteResultProxy> datas) {
		
	}
}
