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
	    
		addSelectionHandler(new SelectionHandler<Widget>() {
			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				Widget selectedItem = event.getSelectedItem();
				TabItemConfig selectedConfig = getConfig(selectedItem);
				String selectedName = selectedConfig.getText();
				
				eventBus.fireEvent(new MainPlaceChangeEvent(selectedName));
			}
		});
	}

	@Override
	public void showItemView(MainItemView itemView) {
		if(itemView != null) {
			TabItemConfig itemConfig = new TabItemConfig();
			itemView.configureTab(itemConfig);
			
			SimpleContainer item = findViewItem(itemConfig.getText());
			
			if(item == null) {
				item = new SimpleContainer();
				item.add(itemView);
				
				add(item, itemConfig);
			}
			setWidget(item);
		}
	}

	private SimpleContainer findViewItem(String name) {
		for(int i = 0 ; i < getWidgetCount() ; i++) {
			SimpleContainer viewItem = (SimpleContainer) getWidget(i);
			TabItemConfig itemConfig = getConfig(viewItem);
			if ((itemConfig.isHTML() ? itemConfig.getHTML() : itemConfig.getText()).equalsIgnoreCase(name)) {
				return viewItem;
			}
		}
		return null;
	}
}
