package com.swg.web.client.presenter;

import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.util.NamedObject;
import com.swg.web.client.view.TabableView;

public interface MainItemPresenter<V extends MainItemView> extends Presenter<V>, NamedObject {
	public static interface MainItemView extends TabableView {
		
	}
}
