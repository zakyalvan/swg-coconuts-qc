package com.swg.web.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.swg.web.client.activity.AppActivityMapper;
import com.swg.web.client.place.AppPlaceHistoryMapper;
import com.swg.web.client.place.SmsServicePlace;

/**
 * Kelas entry-point untuk aplikasi ini.
 * Sesuai namanya, disinilah aplikasi dimulai.
 * 
 * @author zakyalvan
 */
public class QuickCount implements EntryPoint {
	private Logger logger = Logger.getLogger("QuickCount");
	
	private final ClientFactory clientFactory = GWT.create(ClientFactory.class);
	
	public QuickCount() {
		logger.setLevel(Level.FINEST);
	}
	
	@Override
	public void onModuleLoad() {		
//		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
//			@Override
//			public void onUncaughtException(Throwable e) {
//				logger.finest("UncaughtException with message : " + e.getMessage());
//				throw new RuntimeException(e);
//			}
//		});
		
		EventBus eventBus = clientFactory.getEventBus();
		
		PlaceController placeController = clientFactory.getPlaceController();
		Place defaultPlace = new SmsServicePlace("sms");
		
		QuickCountShell display = clientFactory.getApplicationShell();
		
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(display);

		AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		Viewport container = new Viewport();
		
		container.add(display);
		RootPanel.get().add(container, 0, 0);
		
		historyHandler.handleCurrentHistory();
	}
}