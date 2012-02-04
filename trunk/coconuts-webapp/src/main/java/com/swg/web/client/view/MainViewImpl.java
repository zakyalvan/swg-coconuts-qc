package com.swg.web.client.view;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.swg.web.client.event.MainPlaceChangeEvent;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.MainPresenter.MainView;

public class MainViewImpl extends TabPanel implements MainView {	
	@Inject
	public MainViewImpl(final EventBus eventBus) {
		setBodyBorder(true);
	    setTabScroll(true);
	    setCloseContextMenu(true);
	    setAnimScroll(true);
	    
		addSelectionHandler(new SelectionHandler<Widget>() {
			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				Widget selectedItem = event.getSelectedItem();
				CustomTabItemConfig selectedConfig = (CustomTabItemConfig) getConfig(selectedItem);
				String selectedId = selectedConfig.getId();
				
				eventBus.fireEvent(new MainPlaceChangeEvent(selectedId));
			}
		});
	}

	@Override
	public void showItemView(String id, MainItemView itemView) {
		if(itemView != null) {
			CustomTabItemConfig itemConfig = new CustomTabItemConfig();
			itemConfig.setId(id);
			itemView.configureTab(itemConfig);
			
			SimpleContainer item = findViewItem(id);
			
			if(item == null) {
				item = new SimpleContainer();
				item.add(itemView);
				
				add(item, itemConfig);
			}
			setWidget(item);
		}
	}

	private SimpleContainer findViewItem(String id) {
		for(int i = 0 ; i < getWidgetCount() ; i++) {
			SimpleContainer viewItem = (SimpleContainer) getWidget(i);
			CustomTabItemConfig itemConfig = (CustomTabItemConfig) getConfig(viewItem);
			if (itemConfig.getId().equalsIgnoreCase(id)) {
				return viewItem;
			}
		}
		return null;
	}
	
	private class CustomTabItemConfig extends TabItemConfig {
		private String id;
		
		public CustomTabItemConfig() {
			super();
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	}
}
