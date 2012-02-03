package com.swg.web.client;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.swg.web.client.ioc.ClientFactory;
import com.swg.web.client.place.DashBoardPlace;
import com.swg.web.client.place.SmsServicePlace;

public class CoconutsShell extends BorderLayoutContainer {	
	private ClientFactory clientFactory;
	
	@Inject
	public CoconutsShell(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		
		monitorWindowResize = true;
	    Window.enableScrolling(false);
	    setPixelSize(Window.getClientWidth(), Window.getClientHeight());
	    
	    
	    final PlaceController placeController = this.clientFactory.getPlaceController();
	    SimpleContainer menuBarWrapper = new SimpleContainer();
		
		MenuBar menuBar = new MenuBar();
		menuBar.addStyleName(ThemeStyles.getStyle().borderBottom());
		
		MenuBarItem mainBarItem = new MenuBarItem("Utama");
		Menu mainMenu = new Menu();
		
		MenuItem dashboardMenuItem = new MenuItem("Dashboard");
		dashboardMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				placeController.goTo(new DashBoardPlace("dashboard"));
			}
		});
		mainMenu.add(dashboardMenuItem);
		mainBarItem.setMenu(mainMenu);
		menuBar.add(mainBarItem);
		
		MenuBarItem messageBarItem = new MenuBarItem("Kotak Pesan");
		Menu messageMenu = new Menu();
		MenuItem inboxMenuItem = new MenuItem("Pesan Masuk");
		messageMenu.add(inboxMenuItem);
		MenuItem outboxMenuItem = new MenuItem("Pesan Keluar");
		messageMenu.add(outboxMenuItem);
		messageBarItem.setMenu(messageMenu);
		menuBar.add(messageBarItem);
		
		MenuBarItem settingBarItem = new MenuBarItem("Pengaturan");
		Menu settingMenu = new Menu();
		MenuItem smsSettingMenuItem = new MenuItem("Pesan Singkat");
		smsSettingMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				placeController.goTo(new SmsServicePlace("messaging"));
			}
		});
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
}
