package com.swg.web.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.swg.web.client.event.MainPlaceChangeEvent;
import com.swg.web.client.ioc.ClientFactory;
import com.swg.web.client.place.MainPlace;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.MainPresenter;

public class MainActivity extends AbstractActivity implements MainPresenter {
	private ClientFactory clientFactory;
	
	private MainPlace mainPlace;
	
	@Inject
	public MainActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		
		this.clientFactory.getEventBus().addHandler(MainPlaceChangeEvent.TYPE, new MainPlaceChangeEvent.Handler() {
			@Override
			public void onMainPlaceChange(MainPlaceChangeEvent event) {
				changeMainPlace(new MainPlace(event.getName()));
			}
		});
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		/**
		 * Seharusnya dicheck, apakah sebelumnya main place atau bukan. 
		 * Biar ga makan resource untuk ngerender main view berulang kali.
		 */
		MainView mainView = clientFactory.getMainView();
		mainView.showItemView(new MainItemView() {
			private SimpleContainer content = new SimpleContainer();
			
			@Override
			public Widget asWidget() {
				return content;
			}
			
			@Override
			public void configureTab(TabItemConfig config) {
				config.setClosable(true);
				config.setText(mainPlace.getName());
			}
		});
		panel.setWidget(mainView);
	}

	@Override
	public MainView getView() {
		return clientFactory.getMainView();
	}
	public void setMainPlace(MainPlace mainPlace) {
		this.mainPlace = mainPlace;
	}
	
	protected void changeMainPlace(MainPlace newMainPlace) {
		clientFactory.getPlaceController().goTo(newMainPlace);
	}
}
