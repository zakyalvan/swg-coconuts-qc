package com.swg.web.client;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.web.client.mvp.QuickCountPlaceHistoryMapper;

public class QuickCountApps implements Runnable {
	private Logger logger = Logger.getLogger("QuickCountApp");
	
	@Inject
	private EventBus eventBus;

	@Inject
	private PlaceController placeController;

	@Inject
	private ActivityMapper activityMapper;

	@Inject
	private QuickCountShells shell;

	@Override
	public void run() {
		logger.info("Run quick count client application.");
		init();
	}

	private void init() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				
			}
		});
		
		logger.info("Create activity manager.");
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		
		logger.info("Inisiasi history mapper dan history handler.");
		QuickCountPlaceHistoryMapper placeHistoryMapper = GWT.create(QuickCountPlaceHistoryMapper.class);
		Historian historian = GWT.create(DefaultHistorian.class);
		PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper, historian);
		placeHistoryHandler.register(placeController, eventBus, Place.NOWHERE);
		
		
	}
}
