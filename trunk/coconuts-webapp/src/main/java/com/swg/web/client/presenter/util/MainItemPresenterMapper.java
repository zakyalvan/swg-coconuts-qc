package com.swg.web.client.presenter.util;

import java.util.HashMap;
import java.util.Map;

import com.swg.web.client.presenter.MainItemPresenter;

public abstract class MainItemPresenterMapper {
	private Map<String, MainItemPresenter<?>> presenterMap = new HashMap<String, MainItemPresenter<?>>();
	private boolean configured = false;

	public final MainItemPresenter<?> getPresenter(String name) {
		if(!configured) {
			configurePresenterMap(presenterMap);
			configured = true;
		}
		
		if(presenterMap.containsKey(name))
			return presenterMap.get(name);
		
		// Terpaksa di-loop, biar yang case yang berbeda pun ditemukan.
		for(String presenterKey : presenterMap.keySet()) {
			if(presenterKey.equalsIgnoreCase(name)) {
				return presenterMap.get(presenterKey);
			}
		}
		return null;
	}
	
	/**
	 * Jika dikelas turunan memelukan dependency injection, maka method ini ga boleh dipanggil 
	 * constructor. Makanya ada flag {@link #configured}, biar dipanggil di {@link #getPresenter(String)} aja.
	 * 
	 * @param map
	 */
	protected abstract void configurePresenterMap(Map<String, MainItemPresenter<?>> map);
}
