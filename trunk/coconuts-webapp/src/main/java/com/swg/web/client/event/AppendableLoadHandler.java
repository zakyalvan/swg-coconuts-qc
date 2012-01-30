package com.swg.web.client.event;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;

public class AppendableLoadHandler<C, M, D extends ListLoadResult<M>> implements LoadHandler<C, D> {
	private final ListStore<M> store;
	
	public AppendableLoadHandler(ListStore<M> store) {
		this.store = store;
	}
	
	@Override
	public void onLoad(LoadEvent<C, D> event) {
		ListLoadResult<M> loaded = event.getLoadResult();
	    store.replaceAll(loaded.getData());
	}
}
