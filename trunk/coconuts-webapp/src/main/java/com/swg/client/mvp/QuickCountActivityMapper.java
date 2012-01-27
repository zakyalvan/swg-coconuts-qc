package com.swg.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Provider;
import com.swg.client.activity.MainActivity;
import com.swg.client.place.MainPlace;

public class QuickCountActivityMapper implements ActivityMapper {
	/**
	 * Instance dari object ini diretrieve dari ginjector provider,
	 * supaya secara otomatis field yang harus diinject, terinject.
	 */
	private Provider<MainActivity> mainActivityProvider;
	
	@Override
	public Activity getActivity(Place place) {
		if(place instanceof MainPlace) {
			MainPlace mainPlace = (MainPlace) place;
			MainActivity activity = mainActivityProvider.get();
			activity.setId(mainPlace.getName());
			return activity;
		}
		return null;
	}
}
