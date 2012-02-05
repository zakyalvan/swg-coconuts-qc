package com.swg.web.client.presenter.impl;

import java.util.Date;

import com.google.gwt.event.shared.ResettableEventBus;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.VoteObserverPresenter.VoteObserverView;
import com.swg.web.client.view.DataContainer;
import com.swg.web.shared.proxy.VoteObserverProxy;

public class VoteObserverPresenter implements MainItemPresenter<VoteObserverView> {
	private static final long serialVersionUID = -5493858453111253505L;
	
	public interface VoteObserverView extends MainItemView, DataContainer<VoteObserverProxy, Date> {
		public static final String DELETE_BUTTON_ID = "delete-button";
		public static final String ADD_BUTTON_ID = "add-button";
		public static final String UPLOAD_BUTTON_ID = "upload-button";
	}

	public static final String NAME = "vote-observers";
	
	private ResettableEventBus eventBus;
	private VoteObserverView voteObserverView;
	
	private boolean interactive = false;
	private String name = NAME;
	
	@Inject
	public VoteObserverPresenter(EventBus eventBus, VoteObserverView voteObserverView) {
		this.eventBus = new ResettableEventBus(eventBus);
		this.voteObserverView = voteObserverView;
	}
	
	@Override
	public VoteObserverView getView() {
		return voteObserverView;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public boolean isInteractive() {
		return interactive;
	}
	@Override
	public void setInteractive(boolean interactive) {
		this.interactive = interactive;
	}
}
