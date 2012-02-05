package com.swg.web.client.activity;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.ResettableEventBus;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.swg.web.client.event.MainPlaceChangeEvent;
import com.swg.web.client.ioc.ClientFactory;
import com.swg.web.client.place.MainPlace;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainPresenter;
import com.swg.web.client.presenter.util.MainItemPresenterMapper;

public class MainActivity extends AbstractActivity implements MainPresenter {
	private Logger logger = Logger.getLogger("MainActivity");
	
	private ResettableEventBus resettableEventBus;
	
	private ClientFactory clientFactory;
	
	private MainPlace mainPlace;
	
	private MainItemPresenter<?> currentMainItemPresenter;
	
	@Inject
	public MainActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		
		resettableEventBus = new ResettableEventBus(clientFactory.getEventBus());
		resettableEventBus.addHandler(MainPlaceChangeEvent.TYPE, new MainPlaceChangeEvent.Handler() {
			@Override
			public void onMainPlaceChange(MainPlaceChangeEvent event) {
				logger.info("=========== Main activity (place) change.");
				changeMainPlace(new MainPlace(event.getName()));
			}
		});
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		logger.fine("Start main activity for place " + mainPlace);
		
		MainView mainView = clientFactory.getMainView();
		
		MainItemPresenterMapper mainItemPresenterMapper = clientFactory.getMainItemPresenterMapper();
		MainItemPresenter<?> mainItemPresenter = mainItemPresenterMapper.getPresenter(mainPlace.getName());
		
		if(mainItemPresenter == null) {
			// Mungkin lebih bagus ngelempar exception tertentu di sini.
			MessageBox messageBox = new MessageBox("Tidak Ditemukan");
			messageBox.setMessage("Alamat yang Anda tuju tidak ditemukan.");
			messageBox.setIcon(MessageBox.ICONS.error());
			messageBox.show();
		}
		else {
			mainView.showItemView(mainItemPresenter.getName(), mainItemPresenter.getView());
		}
		
		/**
		 * Seharusnya dicheck, apakah sebelumnya main place atau bukan. 
		 * Biar ga makan resource untuk ngerender main view berulang kali.
		 */
		panel.setWidget(mainView);

		currentMainItemPresenter = mainItemPresenter;
		currentMainItemPresenter.setInteractive(true);
	}

	@Override
	public void onCancel() {
		logger.fine("On activity ("+this+") cancel. Reset internal event bus.");
		
		if(currentMainItemPresenter != null)
			currentMainItemPresenter.setInteractive(false);
		
		resettableEventBus.removeHandlers();
	}
	@Override
	public void onStop() {
		logger.fine("On activity ("+this+") stop. Reset and remove internal event bus.");
		currentMainItemPresenter.setInteractive(false);
		resettableEventBus.removeHandlers();
		resettableEventBus = null;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mainPlace == null) ? 0 : mainPlace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainActivity other = (MainActivity) obj;
		if (mainPlace == null) {
			if (other.mainPlace != null)
				return false;
		} else if (!mainPlace.equals(other.mainPlace))
			return false;
		return true;
	}
}
