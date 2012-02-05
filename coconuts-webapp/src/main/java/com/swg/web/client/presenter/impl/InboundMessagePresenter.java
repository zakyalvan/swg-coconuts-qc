package com.swg.web.client.presenter.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.InboundMessagePresenter.InboundMessageView;
import com.swg.web.client.view.DataContainer;
import com.swg.web.shared.proxy.InboundMessageProxy;
import com.swg.web.shared.request.MessagingRequestFactory;
import com.swg.web.shared.request.MessagingRequestFactory.InboundMessageRepositoryRequest;

/**
 * Presenter yang berhubungan dengan aktivitas management pesan masuk.
 * 
 * @author zakyalvan
 */
public class InboundMessagePresenter implements MainItemPresenter<InboundMessageView> {
	private static final long serialVersionUID = -5304944084155370909L;

	public static final String NAME = "inbound-message";
	
	@Inject
	private InboundMessageView inboundMessageView;
	
	@Inject
	private MessagingRequestFactory messagingRequestFactory;
	
	private boolean interactive = false;
	
	@Override
	public InboundMessageView getView() {
		return inboundMessageView;
	}
	@Override
	public String getName() {
		return NAME;
	}
	
	protected List<InboundMessageProxy> loadInboundMessages() {
		return null;
	}
	
	public interface InboundMessageView extends MainItemView, DataContainer<InboundMessageProxy, Date> {
		public static final String REPROCESS_BUTTON_ID = "reprocess-button";
		public static final String SETTING_BUTTON_ID = "setting-button";
	}

	@Override
	public boolean isInteractive() {
		return interactive;
	}
	@Override
	public void setInteractive(boolean interactive) {
		if(interactive) {
			loadDatas();
		}
		this.interactive = interactive;
	}
	
	protected void loadDatas() {
		InboundMessageRepositoryRequest request = messagingRequestFactory.getInboundMessageRepositoryRequest();
		List<InboundMessageProxy> datas = Arrays.<InboundMessageProxy> asList(request.create(InboundMessageProxy.class));
		inboundMessageView.setDatas(datas);
	}
	protected void loadDatas(Date lastVersion) {
		
	}
}
