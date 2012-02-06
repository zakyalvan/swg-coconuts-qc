package com.swg.web.client.mvp;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.swg.web.client.activity.MainActivity;
import com.swg.web.client.activity.ReportingActivity;
import com.swg.web.client.ioc.ClientFactory;
import com.swg.web.client.place.MainPlace;
import com.swg.web.client.place.ReportingPlace;

/**
 * Kelas yang mapping antara place dan activitas yang terkait.
 * 
 * @author zakyalvan
 */
public class CoconutsActivityMapper implements ActivityMapper {
	private Logger logger = Logger.getLogger("CoconutsActivityMapper");
	
	@Inject
	private ClientFactory clientFactory;
	
	@Override
	public Activity getActivity(Place place) {
		if(place instanceof MainPlace) {
			MainPlace mainPlace = (MainPlace) place;
			MainActivity mainActivity = clientFactory.getMainActivity();
			mainActivity.setMainPlace(mainPlace);
			
			logger.fine("Return main activity : " + mainActivity);
			return mainActivity;
		}
		else if(place instanceof ReportingPlace) {
			ReportingPlace reportingPlace = (ReportingPlace) place;
			ReportingActivity reportingActivity = clientFactory.getReportingActivity();
			reportingActivity.setReportingPlace(reportingPlace);
			return reportingActivity;
		}
		return null;
	}
}
