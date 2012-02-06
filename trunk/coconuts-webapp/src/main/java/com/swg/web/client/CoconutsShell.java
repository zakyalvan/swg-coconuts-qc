package com.swg.web.client;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.swg.web.client.ioc.ClientFactory;
import com.swg.web.client.place.MainPlace;
import com.swg.web.client.presenter.impl.InboundMessagePresenter;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter;
import com.swg.web.client.presenter.impl.SiteReportPresenter;
import com.swg.web.client.presenter.impl.SmsServicePresenter;
import com.swg.web.client.presenter.impl.VoteCountingPresenter;
import com.swg.web.client.presenter.impl.VoteObserverPresenter;
import com.swg.web.client.presenter.impl.VoteRecapitulatePresenter;

/**
 * Shell applikasi.
 * 
 * @author zakyalvan
 */
public class CoconutsShell extends BorderLayoutContainer implements SelectionHandler<Item> {
	private ClientFactory clientFactory;
	
	private static final String TARGET_PLACE_KEY = "target-place";
	
	@Inject
	public CoconutsShell(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		
		monitorWindowResize = true;
	    Window.enableScrolling(false);
	    setPixelSize(Window.getClientWidth(), Window.getClientHeight());
	    	    
	    SimpleContainer menuBarWrapper = new SimpleContainer();
		
		MenuBar menuBar = new MenuBar();
		menuBar.addStyleName(ThemeStyles.getStyle().borderBottom());
		
		MenuBarItem mainBarItem = new MenuBarItem("Utama");
		Menu mainMenu = new Menu();
		
		MenuItem dashboardMenuItem = new MenuItem("Dashboard");
		dashboardMenuItem.setData(TARGET_PLACE_KEY, new MainPlace("dashboard"));
		dashboardMenuItem.addSelectionHandler(this);
		mainMenu.add(dashboardMenuItem);
		MenuItem voteObserverMenuItem = new MenuItem("Data Pemantau");
		voteObserverMenuItem.setData(TARGET_PLACE_KEY, new MainPlace(VoteObserverPresenter.NAME));
		voteObserverMenuItem.addSelectionHandler(this);
		mainMenu.add(voteObserverMenuItem);
		mainBarItem.setMenu(mainMenu);
		menuBar.add(mainBarItem);
		
		MenuBarItem voteBarItem = new MenuBarItem("Perhitungan");
		Menu voteMenu = new Menu();
		MenuItem voteCountingMenuItem = new MenuItem("Rincian Perolehan Suara");
		voteCountingMenuItem.setData(TARGET_PLACE_KEY, new MainPlace(VoteCountingPresenter.NAME));
		voteCountingMenuItem.addSelectionHandler(this);
		voteMenu.add(voteCountingMenuItem);
		MenuItem voteRecapMenuItem = new MenuItem("Rekapitulasi Suara");
		voteRecapMenuItem.setData(TARGET_PLACE_KEY, new MainPlace(VoteRecapitulatePresenter.NAME));
		voteRecapMenuItem.addSelectionHandler(this);
		voteMenu.add(voteRecapMenuItem);
		
		voteMenu.add(new SeparatorMenuItem());
		
		MenuItem siteReportMenuItem = new MenuItem("Laporan Lapangan");
		siteReportMenuItem.setData(TARGET_PLACE_KEY, new MainPlace(SiteReportPresenter.NAME));
		siteReportMenuItem.addSelectionHandler(this);
		voteMenu.add(siteReportMenuItem);
		voteBarItem.setMenu(voteMenu);
		menuBar.add(voteBarItem);
		
		MenuBarItem messageBarItem = new MenuBarItem("Kotak Pesan");
		Menu messageMenu = new Menu();
		MenuItem inboxMenuItem = new MenuItem("Pesan Masuk");
		inboxMenuItem.setData(TARGET_PLACE_KEY, new MainPlace(InboundMessagePresenter.NAME));
		inboxMenuItem.addSelectionHandler(this);
		messageMenu.add(inboxMenuItem);
		MenuItem outboxMenuItem = new MenuItem("Pesan Keluar");
		outboxMenuItem.setData(TARGET_PLACE_KEY, new MainPlace(OutboundMessagePresenter.NAME));
		outboxMenuItem.addSelectionHandler(this);
		messageMenu.add(outboxMenuItem);
		messageBarItem.setMenu(messageMenu);
		menuBar.add(messageBarItem);
		
		MenuBarItem settingBarItem = new MenuBarItem("Pengaturan");
		Menu settingMenu = new Menu();
		MenuItem smsSettingMenuItem = new MenuItem("Service Sms");
		smsSettingMenuItem.setData(TARGET_PLACE_KEY, new MainPlace(SmsServicePresenter.NAME));
		smsSettingMenuItem.addSelectionHandler(this);
		settingMenu.add(smsSettingMenuItem);
		settingBarItem.setMenu(settingMenu);
		menuBar.add(settingBarItem);
		
		MenuBarItem helpBarItem = new MenuBarItem("Bantuan");
		Menu helpMenu = new Menu();

		MenuItem aboutMenuItem = new MenuItem("Tentang Aplikasi");
		helpMenu.add(aboutMenuItem);
		
		helpBarItem.setMenu(helpMenu);
		menuBar.add(helpBarItem);
		
		menuBarWrapper.add(menuBar);
	
		setNorthWidget(menuBarWrapper, new BorderLayoutData(28));
	}
	@Override
	protected void onWindowResize(int width, int height) {
		super.onWindowResize(width, height);
		setPixelSize(width, height);
	}
	
	@Override
	public void onSelection(SelectionEvent<Item> event) {
		if(event.getSelectedItem().getId() != null || event.getSelectedItem().getId().length() != 0) {
			PlaceController placeController = clientFactory.getPlaceController();
			
			Object maybePlace = event.getSelectedItem().getData(TARGET_PLACE_KEY);
			if(maybePlace != null && maybePlace instanceof Place) {
				placeController.goTo((Place) maybePlace);
			}
			else {
				ConfirmMessageBox confirmBox = new ConfirmMessageBox("Tidak Ada Target", "Menu item yang Anda pilih tidak memiliki keterangan target.");
				confirmBox.show();
			}
		}
	}
}
