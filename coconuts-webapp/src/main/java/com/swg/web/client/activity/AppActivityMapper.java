package com.swg.web.client.activity;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.swg.web.client.ClientFactory;
import com.swg.web.client.place.DashBoardPlace;
import com.swg.web.client.place.SmsServicePlace;
import com.swg.web.client.place.SmsServiceSettingPlace;
import com.swg.web.client.place.VoteCountingPlace;
import com.swg.web.client.place.VoteObserverPlace;

/**
 * This class map each activity to it's corresponding place.
 * 
 * @author zakyalvan
 */
public class AppActivityMapper implements ActivityMapper {
	private Logger logger = Logger.getLogger("AppActivityMapper");
	
	private ClientFactory clientFactory;
	
	public AppActivityMapper(ClientFactory clientFactory) {
		logger.setLevel(Level.FINEST);
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		logger.info("Retrieve activity of place : " + place);
		
		Activity activity = null;
		
		if(place instanceof DashBoardPlace)
			activity = clientFactory.getDashBoardActivity();
		else if(place instanceof VoteObserverPlace)
			activity = clientFactory.getVoteObserverMngmntActivity();
		else if(place instanceof SmsServicePlace)
			activity = clientFactory.getMessagingMngmntActivity();
		else if(place instanceof SmsServiceSettingPlace)
			activity = clientFactory.getSmsServiceSettingActivity();
		else if(place instanceof VoteCountingPlace)
			activity = clientFactory.getVoteCountingActivity();
		
		if(activity != null)
			logger.info("Found activity with type : " + activity);
		else
			logger.info("Activity for place ("+ place +")not found.");
		
		return activity;
	}
}
