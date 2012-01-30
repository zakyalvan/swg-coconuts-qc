package com.swg.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.RequestTransport;
import com.swg.web.client.presenter.DashBoardPresenter;
import com.swg.web.client.presenter.SmsServicePresenter;
import com.swg.web.client.presenter.SmsServiceSettingPresenter;
import com.swg.web.client.presenter.VoteCountingPresenter;
import com.swg.web.client.presenter.VoteObserverPresenter;
import com.swg.web.client.view.widget.DashBoardWidget;
import com.swg.web.client.view.widget.SerialGatewayEditorWidget;
import com.swg.web.client.view.widget.SmsServiceSettingWidget;
import com.swg.web.client.view.widget.SmsServiceWidget;
import com.swg.web.client.view.widget.VoteCountingWidget;
import com.swg.web.client.view.widget.VoteObserverWidget;
import com.swg.web.shared.request.BaseRequestFactory;
import com.swg.web.shared.request.CustomRequestTransport;
import com.swg.web.shared.request.MessagingRequestFactory;
import com.swg.web.shared.request.SecurityRequestFactory;
import com.swg.web.shared.request.VoteObserverRequestFactory;

/**
 * Module gin untuk aplikasi ini.
 * Gin adalah ioc container untuk client side berbasis gwt.
 * 
 * @author zakyalvan
 *
 */
public class QuickCountModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(EventBus.class).toProvider(EventBusProvider.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class).in(Singleton.class);
		
		bind(RequestTransport.class).toProvider(RequestTransportProvider.class);
		
		bind(QuickCountShell.class).in(Singleton.class);
		bind(DashBoardPresenter.View.class).to(DashBoardWidget.class).in(Singleton.class);
		bind(VoteObserverPresenter.View.class).to(VoteObserverWidget.class).in(Singleton.class);
		bind(VoteCountingPresenter.View.class).to(VoteCountingWidget.class).in(Singleton.class);
		bind(SmsServicePresenter.View.class).to(SmsServiceWidget.class).in(Singleton.class);
		bind(SmsServiceSettingPresenter.View.class).to(SmsServiceSettingWidget.class).in(Singleton.class);
		bind(SerialGatewayEditorWidget.class).in(Singleton.class);
		
		bind(SecurityRequestFactory.class).toProvider(SecurityRequestFactoryProvider.class).in(Singleton.class);
		bind(BaseRequestFactory.class).toProvider(BaseRequestFactoryProvider.class).in(Singleton.class);
		bind(VoteObserverRequestFactory.class).toProvider(VoteObserverRequestFactoryProvider.class).in(Singleton.class);
		bind(MessagingRequestFactory.class).toProvider(MessagingRequestFactoryProvider.class).in(Singleton.class);
	}
	
	public static class EventBusProvider implements Provider<EventBus> {
		@Override
		public EventBus get() {
			EventBus eventBus = new SimpleEventBus();
			return eventBus;
		}
	}
	
	/**
	 * Provider untuk custom transport provider.
	 * Apakah transport provider singleton atau bagaimana ya.
	 * 
	 * @author zakyalvan
	 */
	public static class RequestTransportProvider implements Provider<RequestTransport> {
		@Override
		public RequestTransport get() {
			CustomRequestTransport requestTransport = new CustomRequestTransport();
			return requestTransport;
		}
	}
	
	/**
	 * {@link Provider} untuk {@link PlaceController}
	 * 
	 * @author zakyalvan
	 */
	public static class PlaceControllerProvider implements Provider<PlaceController> {
		private EventBus eventBus;
		
		@Inject(optional=false)
		public PlaceControllerProvider(EventBus eventBus) {
			this.eventBus = eventBus;
		}
		
		@Override
		public PlaceController get() {
			/**
			 * Create custom delegate untuk place controller.
			 * Kalau default delegate menggunakan Window.alert (Native alert browser),
			 * dengan custom delegate ini pake {@link ConfirmMessageBox} bawaan ext-gwt.
			 * Sebenarnya bisa di ganti di module, cuman repot (Ga ngerti saya).
			 * Baca juga apa itu {@link PlaceController.Delegate}.
			 */
//			PlaceController.Delegate customDelegate = new PlaceController.Delegate() {
//				@Override
//				public boolean confirm(String message) {
//					ConfirmMessageBox confirmMessageBox = new ConfirmMessageBox("Confirm", message);
//					confirmMessageBox.setModal(true);
//					confirmMessageBox.show();
//					//if(confirmMessageBox.get)
//					return false;
//				}
//				
//				@Override
//				public HandlerRegistration addWindowClosingHandler(ClosingHandler handler) {
//					return Window.addWindowClosingHandler(handler);
//				}
//			};
			return new PlaceController(eventBus);
		}
	}
	
	public static class BaseRequestFactoryProvider implements Provider<BaseRequestFactory> {
		private EventBus eventBus;
		private RequestTransport requestTransport;
		
		@Inject(optional=false)
		public BaseRequestFactoryProvider(EventBus eventBus, RequestTransport requestTransport) {
			this.eventBus = eventBus;
			this.requestTransport = requestTransport;
		}
		
		@Override
		public BaseRequestFactory get() {
			BaseRequestFactory requestFactory = GWT.create(BaseRequestFactory.class);
			requestFactory.initialize(eventBus, requestTransport);
			return requestFactory;
		}
	}
	
	/**
	 * Provider untuk object ini dalam module (ioc) gin untuk aplikasi ini.
	 * Kelas ini gwt-related.
	 * 
	 * @author zakyalvan
	 *
	 */
	public static class VoteObserverRequestFactoryProvider implements com.google.inject.Provider<VoteObserverRequestFactory> {
		private EventBus eventBus;
		private RequestTransport requestTransport;
		
		@Inject
		public VoteObserverRequestFactoryProvider(EventBus eventBus, RequestTransport requestTransport) {
			this.eventBus = eventBus;
			this.requestTransport = requestTransport;
		}
		@Override
		public VoteObserverRequestFactory get() {
			VoteObserverRequestFactory requestFactory = GWT.create(VoteObserverRequestFactory.class);
			requestFactory.initialize(eventBus, requestTransport);
			return requestFactory;
		}
	}
	
	
	public static class MessagingRequestFactoryProvider implements com.google.inject.Provider<MessagingRequestFactory> {
		private EventBus eventBus;
		private RequestTransport requestTransport;
		
		@Inject(optional=false)
		public MessagingRequestFactoryProvider(EventBus eventBus, RequestTransport requestTransport) {
			this.eventBus = eventBus;
			this.requestTransport = requestTransport;
		}

		@Override
		public MessagingRequestFactory get() {
			MessagingRequestFactory requestFactory = GWT.create(MessagingRequestFactory.class);
			requestFactory.initialize(eventBus, requestTransport);
			return requestFactory;
		}
	}
	
	public static class SecurityRequestFactoryProvider implements Provider<SecurityRequestFactory> {
		private EventBus eventBus;
		private RequestTransport requestTransport;
		
		@Inject(optional=false)
		public SecurityRequestFactoryProvider(EventBus eventBus, RequestTransport requestTransport) {
			this.eventBus = eventBus;
			this.requestTransport = requestTransport;
		}
	
		@Override
		public SecurityRequestFactory get() {
			SecurityRequestFactory requestFactory = GWT.create(SecurityRequestFactory.class);
			requestFactory.initialize(eventBus, requestTransport);
			return requestFactory;
		}
	}
}
