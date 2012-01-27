package com.swg.client;

import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.swg.client.place.DashBoardPlace;
import com.swg.client.place.SmsServicePlace;
import com.swg.client.view.TabableView;
import com.swg.client.view.WindowableView;

/**
 * Ini merupakan main layout untuk aplikasi ini.
 * 
 * @author zakyalvan
 */
public class QuickCountShell implements IsWidget, AcceptsOneWidget {
	private Logger logger = Logger.getLogger("QuickCountShell");
		
	private EventBus eventBus;
	private PlaceController placeController;
	
	private SimpleContainer mainContainer;
	private TabPanel tabContainer;
	
	@Inject
	public QuickCountShell(EventBus eventBus, PlaceController placeController) {
		this.eventBus = eventBus;
		this.placeController = placeController;
	}
	
	@Override
	public Widget asWidget() {
		mainContainer = new SimpleContainer();
		
		BorderLayoutContainer contentContainer = new BorderLayoutContainer();
		
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
	
		contentContainer.setNorthWidget(menuBarWrapper, new BorderLayoutData(28));
		
		BorderLayoutData eastData = new BorderLayoutData(200);
		eastData.setMargins(new Margins(5, 5, 5, 0));
		eastData.setCollapseMini(true);
		eastData.setCollapsible(true);
		
		contentContainer.add(new SimplePanel(), eastData);
		
		BorderLayoutData westData = new BorderLayoutData(200);
		westData.setMargins(new Margins(5, 0, 5, 5));
		westData.setCollapseMini(true);
		westData.setCollapsible(true);
		
		contentContainer.add(new SimplePanel(), westData);
		
		MarginData centerData = new MarginData(5);
		
		tabContainer = new TabPanel();
		SimpleContainer centerContainer = new SimpleContainer();
		centerContainer.setWidget(tabContainer);
		contentContainer.add(centerContainer, centerData);
		
		mainContainer.add(contentContainer);
		return mainContainer;
	}
	
	@Override
	public void setWidget(IsWidget widget) {
		logger.info("Tampilin view, pertama chek jenis dari view yang akan ditampilin.");
		if(widget instanceof WindowableView) {
			showWindowableView((WindowableView) widget);
		}
		else if(widget instanceof TabableView) {
			showTabableView((TabableView) widget);
		}
		else {
			mainContainer.setWidget(widget);
		}
		
		logger.info("Force layout!");
		mainContainer.forceLayout();
	}

	protected void showWindowableView(WindowableView windowableView) {
		logger.info("Show windowable view : " + windowableView);
		Window window = new Window();
		windowableView.configureWindow(window);
		window.setVisible(true);
	}
	protected void showTabableView(TabableView tabableView) {
		logger.info("Add to tab container a tabbable view : " + tabableView);
		TabItemConfig config = new TabItemConfig();
		tabableView.configureTab(config);
		tabContainer.add(tabableView.asWidget(), config);
	}
}