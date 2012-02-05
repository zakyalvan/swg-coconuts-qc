package com.swg.web.client.presenter.impl;

import com.google.gwt.event.shared.ResettableEventBus;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.web.client.event.WidgetTimerTimeoutEvent;
import com.swg.web.client.event.WidgetTimerTimeoutEvent.WidgetTimerTimeoutHandler;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.VoteRecapitulatePresenter.VoteRecapitulateView;

public class VoteRecapitulatePresenter implements MainItemPresenter<VoteRecapitulateView>, WidgetTimerTimeoutHandler {
	private static final long serialVersionUID = 333447741863638163L;

	public interface VoteRecapitulateView extends MainItemView {
		
	}

	public static final String NAME = "vote-recapitulate";
	
	private ResettableEventBus resettableEventBus;
	private VoteRecapitulateView voteRecapitulateView;
	
	private boolean interactive;
	
	@Inject
	public VoteRecapitulatePresenter(EventBus eventBus, VoteRecapitulateView voteRecapitulateView) {
		this.resettableEventBus = new ResettableEventBus(eventBus);
		this.voteRecapitulateView = voteRecapitulateView;
	}
	
	@Override
	public VoteRecapitulateView getView() {
		return voteRecapitulateView;
	}
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public boolean isInteractive() {
		return interactive;
	}
	@Override
	public void setInteractive(boolean interactive) {
		this.interactive = interactive;
		
		if(interactive) {
			resettableEventBus.addHandler(WidgetTimerTimeoutEvent.TYPE, this);
		}
		else {
			resettableEventBus.removeHandlers();
		}
	}

	@Override
	public void onWidgetTimerTimeout(WidgetTimerTimeoutEvent timeoutEvent) {
		
	}
}
