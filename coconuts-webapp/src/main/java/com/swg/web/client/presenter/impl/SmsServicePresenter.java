package com.swg.web.client.presenter.impl;

import com.google.gwt.event.shared.ResettableEventBus;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.SmsServicePresenter.SmsServiceView;
import com.swg.web.shared.request.MessagingRequestFactory;

/**
 * Kontrak untuk presenter sms service management.
 * 
 * @author zakyalvan
 */
public class SmsServicePresenter implements MainItemPresenter<SmsServiceView> {
	private static final long serialVersionUID = 9194302431902268725L;
	
	public interface SmsServiceView extends MainItemView {
		public static final String START_SERVICE_TRIGGER_ID = "start-service-trigger";
		public static final String STOP_SERVICE_TRIGGER_ID = "stop-service-trigger";
		
		/**
		 * Dipanggil oleh object konkrit presenter untuk menotifikasi bahwa service distart.
		 * Bisa jadi notifikasi ini merupakan notifikasi global.
		 */
		void notifyServiceStarted();

		/**
		 * Dipanggil oleh presenter untuk menotifikasi bahwa service gagal di-start.
		 * 
		 * @param failureCause
		 */
		void notifyServiceStartingFailed(Throwable failureCause);
		void notifyServiceStoped();
		void notifyServiceStopingFailed(Throwable failureCause);
	}
	
	public static final String NAME = "sms-service";
	
	private ResettableEventBus resettableEventBus;
	private SmsServiceView smsServiceView;
	private MessagingRequestFactory messagingRequestFactory;
	
	private boolean interactive = false;
	
	@Inject
	public SmsServicePresenter(EventBus eventBus, SmsServiceView smsServiceView, MessagingRequestFactory messagingRequestFactory) {
		this.resettableEventBus = new ResettableEventBus(eventBus);
		this.smsServiceView = smsServiceView;
		this.messagingRequestFactory = messagingRequestFactory;
	}
	
	@Override
	public SmsServiceView getView() {
		return smsServiceView;
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
	}
	
	protected void startService() {
		
	}
	protected void stopService() {
		
	}
}
