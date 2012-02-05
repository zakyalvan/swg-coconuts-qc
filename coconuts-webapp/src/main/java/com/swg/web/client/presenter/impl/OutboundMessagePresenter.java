package com.swg.web.client.presenter.impl;

import java.util.Date;

import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.swg.web.client.event.WidgetTimerTimeoutEvent;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter.OutboundMessageView;
import com.swg.web.client.view.DataContainer;
import com.swg.web.shared.proxy.OutboundMessageProxy;
import com.swg.web.shared.request.MessagingRequestFactory;
import com.swg.web.shared.request.MessagingRequestFactory.MessageProcessingRequest;

/**
 * Presenter untuk nampilin data pesan keluar yang sudah pernah dikirim
 * beserta atribute lainnya.
 * 
 * @author zakyalvan
 */
public class OutboundMessagePresenter implements MainItemPresenter<OutboundMessageView>, WidgetTimerTimeoutEvent.Handler {
	private static final long serialVersionUID = 3195192944784699804L;

	public static final String NAME = "outbound-message";
	
	private OutboundMessageView outboundMessageView;
	private MessagingRequestFactory messagingRequestFactory;
	private ResettableEventBus interactiveEventBus;
	
	private boolean interactive = false;
	
	@Inject
	public OutboundMessagePresenter(EventBus eventBus, MessagingRequestFactory requestFactory, OutboundMessageView outboundMessageView) {
		this.outboundMessageView = outboundMessageView;
		this.messagingRequestFactory = requestFactory;
		this.interactiveEventBus = new ResettableEventBus(eventBus);
	}
	
	@Override
	public OutboundMessageView getView() {
		return outboundMessageView;
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
	public void setIteractive(boolean interactive) {
		this.interactive = interactive;
		if(interactive) {
			interactiveEventBus.addHandler(WidgetTimerTimeoutEvent.TYPE, this);
		}
		else {
			interactiveEventBus.removeHandlers();
		}
	}
	
	protected void loadDatas() {
		
	}
	protected void loadDatas(Date lastVersion) {
		
	}
	
	// Test method aja ini.
	protected void sendMessage(String recipient, String content) {
		MessageProcessingRequest messageProcessingRequest = messagingRequestFactory.getMessageProcessingRequest();
		OutboundMessageProxy outboundMessageProxy = messageProcessingRequest.create(OutboundMessageProxy.class);
		messageProcessingRequest.edit(outboundMessageProxy);
		
		outboundMessageProxy.setContent(content);
		outboundMessageProxy.setRecipient(recipient);
		
		messageProcessingRequest.processMessage(outboundMessageProxy).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				Window.alert("Pengiriman Pesan Berhasil!");
			}
		});
	}
	
	@Override
	public void onWidgetTimerTimeout(WidgetTimerTimeoutEvent timeoutEvent) {
		// do reload or/and other scheduled activity.
		if(outboundMessageView.isAutoreloadEnabled()) {
			// Buat test aja ini.
			sendMessage("+6281339732758", "Test Content");
			
			if(outboundMessageView.isAutoreloadPartial()) {
				if(outboundMessageView.getLastDataVersion() == null)
					loadDatas();
				else
					loadDatas(outboundMessageView.getLastDataVersion());
			}
			else {
				loadDatas();
			}
		}
	}
	
	public interface OutboundMessageView extends MainItemView, DataContainer<OutboundMessageProxy, Date> {
		public static final String COMPOSE_TRIGGER_ID = "compose-trigger";
		public static final String RESEND_TRIGGER_ID = "resend-trigger";
		public static final String RELOAD_TRIGGER_ID = "reload-trigger";
		public static final String AUTORELOAD_TRIGGER_ID = "autoreload-trigger";
	}
}
