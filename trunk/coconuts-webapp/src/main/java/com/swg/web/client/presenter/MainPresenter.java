package com.swg.web.client.presenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.swg.web.client.place.MainPlace;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.MainPresenter.MainView;

public interface MainPresenter extends Presenter<MainView> {
	public interface MainView extends IsWidget {
		void showItemView(MainItemView itemView);
	}
	
	public void setMainPlace(MainPlace newMainPlace);
}
