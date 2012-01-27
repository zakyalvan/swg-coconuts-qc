package com.swg.client.presenter;

import com.swg.client.presenter.DashBoardPresenter.View;
import com.swg.client.view.TabableView;

public interface DashBoardPresenter extends Presenter<View> {
	public interface View extends TabableView {
		
	}
	
}
