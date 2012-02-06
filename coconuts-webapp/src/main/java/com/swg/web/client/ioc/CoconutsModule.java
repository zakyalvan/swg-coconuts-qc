package com.swg.web.client.ioc;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.RequestTransport;
import com.swg.web.client.CoconutsApplication;
import com.swg.web.client.CoconutsShell;
import com.swg.web.client.activity.MainActivity;
import com.swg.web.client.activity.ReportingActivity;
import com.swg.web.client.mvp.CoconutsActivityMapper;
import com.swg.web.client.presenter.MainPresenter.MainView;
import com.swg.web.client.presenter.ReportingPresenter.ReportingView;
import com.swg.web.client.presenter.SmsServiceSettingPresenter;
import com.swg.web.client.presenter.impl.DashBoardPresenter;
import com.swg.web.client.presenter.impl.InboundMessagePresenter;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter;
import com.swg.web.client.presenter.impl.SiteReportPresenter;
import com.swg.web.client.presenter.impl.SmsServicePresenter;
import com.swg.web.client.presenter.impl.VoteCountingPresenter;
import com.swg.web.client.presenter.impl.VoteObserverPresenter;
import com.swg.web.client.presenter.impl.VoteRecapitulatePresenter;
import com.swg.web.client.presenter.util.DumbMainItemPresenterMapper;
import com.swg.web.client.presenter.util.MainItemPresenterMapper;
import com.swg.web.client.view.GlobalWidgetTimer;
import com.swg.web.client.view.MainViewImpl;
import com.swg.web.client.view.ReportingViewImpl;
import com.swg.web.client.view.web.DashBoardWidget;
import com.swg.web.client.view.web.InboundMessageWidget;
import com.swg.web.client.view.web.OutboundMessageWidget;
import com.swg.web.client.view.web.SiteReportWidget;
import com.swg.web.client.view.web.SmsServiceSettingWidget;
import com.swg.web.client.view.web.SmsServiceWidget;
import com.swg.web.client.view.web.VoteCountingWidget;
import com.swg.web.client.view.web.VoteObserverWidget;
import com.swg.web.client.view.web.VoteRecapitulateWidget;
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
 */
public class CoconutsModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class).in(Singleton.class);
		
		bind(RequestTransport.class).toProvider(RequestTransportProvider.class);
		
		bind(ActivityMapper.class).to(CoconutsActivityMapper.class).in(Singleton.class);
		
		bind(CoconutsApplication.class).in(Singleton.class);
		bind(CoconutsShell.class).in(Singleton.class);
		
		bind(GlobalWidgetTimer.class).in(Singleton.class);
		
		bind(MainView.class).to(MainViewImpl.class).in(Singleton.class);
		bind(MainActivity.class);
		
		bind(ReportingView.class).to(ReportingViewImpl.class).in(Singleton.class);
		bind(ReportingActivity.class);
		
		bind(SecurityRequestFactory.class).toProvider(SecurityRequestFactoryProvider.class).in(Singleton.class);
		bind(BaseRequestFactory.class).toProvider(BaseRequestFactoryProvider.class).in(Singleton.class);
		bind(VoteObserverRequestFactory.class).toProvider(VoteObserverRequestFactoryProvider.class).in(Singleton.class);
		bind(MessagingRequestFactory.class).toProvider(MessagingRequestFactoryProvider.class).in(Singleton.class);
		
		bind(MainItemPresenterMapper.class).to(DumbMainItemPresenterMapper.class).in(Singleton.class);
		
		bind(DashBoardPresenter.DashBoardView.class).to(DashBoardWidget.class).in(Singleton.class);
		bind(DashBoardPresenter.class).in(Singleton.class);
		
		bind(InboundMessagePresenter.InboundMessageView.class).to(InboundMessageWidget.class).in(Singleton.class);
		bind(InboundMessagePresenter.class).in(Singleton.class);
		
		bind(OutboundMessagePresenter.OutboundMessageView.class).to(OutboundMessageWidget.class).in(Singleton.class);
		bind(OutboundMessagePresenter.class).in(Singleton.class);
		
		bind(VoteCountingPresenter.VoteCountingView.class).to(VoteCountingWidget.class).in(Singleton.class);
		bind(VoteCountingPresenter.class).in(Singleton.class);
		
		bind(VoteRecapitulatePresenter.VoteRecapitulateView.class).to(VoteRecapitulateWidget.class).in(Singleton.class);
		bind(VoteRecapitulatePresenter.class).in(Singleton.class);
		
		bind(VoteObserverPresenter.VoteObserverView.class).to(VoteObserverWidget.class).in(Singleton.class);
		bind(VoteObserverPresenter.class).in(Singleton.class);
		
		bind(SiteReportPresenter.SiteReportView.class).to(SiteReportWidget.class).in(Singleton.class);
		bind(SiteReportPresenter.class).in(Singleton.class);
		
		bind(SmsServicePresenter.SmsServiceView.class).to(SmsServiceWidget.class).in(Singleton.class);
		bind(SmsServicePresenter.class).in(Singleton.class);
		
		bind(SmsServiceSettingPresenter.View.class).to(SmsServiceSettingWidget.class).in(Singleton.class);
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
		@Inject(optional=false)
		private EventBus eventBus;
		
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
	public static class VoteObserverRequestFactoryProvider implements Provider<VoteObserverRequestFactory> {
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
	
	
	public static class MessagingRequestFactoryProvider implements Provider<MessagingRequestFactory> {
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
