package com.swg.web.client;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.swg.web.client.mvp.CoconutsPlaceHistoryMapper;
import com.swg.web.client.place.MainPlace;

/**
 * Object dalam konteks aplikasi yang dijalankan pertama kali pada saat 
 * aplikasi diload (melalui entry point). Dalam object dari kelas inilah
 * seluruh resource aplikasi di-bootstrap (diinisiasi).
 * 
 * @author zakyalvan
 */
public class CoconutsApplication {
	private Logger logger = Logger.getLogger("CoconutsApplication");
	
	@Inject
	private EventBus eventBus;

	@Inject
	private PlaceController placeController;

	@Inject
	private ActivityMapper activityMapper;

	@Inject
	private CoconutsShell coconutsShell;

	public void run() {
//		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
//			@Override
//			public void onUncaughtException(Throwable e) {
//				
//			}
//		});
		
		logger.info("Create activity manager.");
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		
		SimpleContainer centerDisplay = new SimpleContainer();
		activityManager.setDisplay(centerDisplay);
		coconutsShell.setCenterWidget(centerDisplay, new MarginData(5));
		
		logger.info("Inisiasi history mapper dan history handler.");
		PlaceHistoryMapper historyMapper = GWT.create(CoconutsPlaceHistoryMapper.class);
		Historian historian = GWT.create(DefaultHistorian.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper, historian);
		historyHandler.register(placeController, eventBus, Place.NOWHERE);
		
		Viewport viewport = new Viewport();
		viewport.add(coconutsShell);
		RootPanel.get().add(viewport, 0, 0);
		
		placeController.goTo(MainPlace.DEFAULT);
		
		logger.info("Handler current history.");
		historyHandler.handleCurrentHistory();
	}
}
