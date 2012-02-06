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
import com.swg.web.shared.request.VoteObserverRequestFactory;

public class VoteObserverPresenter implements MainItemPresenter<VoteObserverView> {
	private static final long serialVersionUID = -5493858453111253505L;
	
	public interface VoteObserverView extends MainItemView, DataContainer<VoteObserverProxy, Date> {
		public static final String DELETE_TRIGGER_ID = "delete-trigger";
		public static final String ADD_TRIGGER_ID = "add-trigger";
		public static final String UPLOAD_TRIGGER_ID = "upload-trigger";
		
		public static final String VERIFICATION_TRIGGER_ID = "verivication-trigger";
		public static final String RELOAD_TRIGGER_ID = "reload-trigger";
	}

	public static final String NAME = "vote-observers";
	
	private ResettableEventBus resettableEventBus;
	private VoteObserverView voteObserverView;
	private VoteObserverRequestFactory voteObserverRequestFactory;
	
	private boolean interactive = false;
	private String name = NAME;
	
	@Inject
	public VoteObserverPresenter(EventBus eventBus, VoteObserverView voteObserverView, VoteObserverRequestFactory voteObserverRequestFactory) {
		this.resettableEventBus = new ResettableEventBus(eventBus);
		this.voteObserverView = voteObserverView;
		this.voteObserverRequestFactory = voteObserverRequestFactory;
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
