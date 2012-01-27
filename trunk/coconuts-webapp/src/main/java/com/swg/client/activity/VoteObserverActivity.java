package com.swg.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.swg.client.presenter.VoteObserverPresenter;

/**
 * Aktivitas yang nunjukin urusan data pemantau atau saksi pemungutan suara.
 * 
 * @author zakyalvan
 */
public class VoteObserverActivity extends AbstractActivity implements VoteObserverPresenter {
	private View view;
	
	@Inject
	public VoteObserverActivity(View view) {
		this.view = view;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public View getView() {
		return view;
	}
}
