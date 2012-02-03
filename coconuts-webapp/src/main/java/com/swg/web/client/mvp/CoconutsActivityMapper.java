package com.swg.web.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.swg.web.client.activity.MainActivity;
import com.swg.web.client.ioc.ClientFactory;
import com.swg.web.client.place.MainPlace;

public class CoconutsActivityMapper implements ActivityMapper {
	@Inject
	private ClientFactory clientFactory;
	
	@Override
	public Activity getActivity(Place place) {
		if(place instanceof MainPlace) {
			MainPlace mainPlace = (MainPlace) place;
			MainActivity mainActivity = clientFactory.getMainActivity();
			mainActivity.setMainPlace(mainPlace);
			return mainActivity;
		}
		return null;
	}
}
