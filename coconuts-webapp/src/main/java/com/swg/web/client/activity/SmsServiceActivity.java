package com.swg.web.client.activity;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.sencha.gxt.widget.core.client.info.Info;
import com.swg.web.client.event.AddGatewayRequestEvent;
import com.swg.web.client.event.SmsServiceStartEvent;
import com.swg.web.client.event.SmsServiceStartReqEvent;
import com.swg.web.client.event.SmsServiceStopEvent;
import com.swg.web.client.event.SmsServiceStopReqEvent;
import com.swg.web.client.event.AddGatewayRequestEvent.SupportedGatewayType;
import com.swg.web.client.presenter.SmsServicePresenter;
import com.swg.web.shared.proxy.SerialGatewayInfoProxy;
import com.swg.web.shared.request.MessagingRequestFactory;
import com.swg.web.shared.request.MessagingRequestFactory.SerialGatewayManagementRequest;
import com.swg.web.shared.request.MessagingRequestFactory.ServiceLifecycleRequest;

/**
 * Aktivitas untuk management server sms.
 * 
 * @author zakyalvan
 */
public class SmsServiceActivity extends AbstractActivity implements SmsServicePresenter {
	private Logger logger = Logger.getLogger("SmsServiceActivity");
	
	private EventBus eventBus;
	private View view;
	private MessagingRequestFactory requestFactory;
	
	@Inject(optional=false)
	public SmsServiceActivity(EventBus eventBus, SmsServicePresenter.View view, MessagingRequestFactory requestFactory) {
		logger.setLevel(Level.FINE);
		
		this.eventBus = eventBus;
		this.view = bindView(view);
		this.requestFactory = requestFactory;
		
		configureHandler();
	}
	
	@Override
	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		panel.setWidget(view);
	}

	@Override
	public View getView() {
		return view;
	}

	@Override
	public void startService() {
		logger.info("Starting sms service");
		ServiceLifecycleRequest request = requestFactory.getMessageServiceRequest();
		request.startService().fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				view.notifyServiceStarted();
				
				/**
				 * Mungkin ada pertanyaan, buat apa event ini difire?
				 * Padahal view object sudah dinotify sebelumnya.
				 * Ini untuk global notification.
				 */
				eventBus.fireEvent(new SmsServiceStartEvent());
			}
			@Override
			public void onFailure(ServerFailure error) {
				view.notifyServiceStartingFailed(new RuntimeException(error.getMessage()));
				
				/**
				 * Di-fire untuk global notification.
				 */
				eventBus.fireEvent(new SmsServiceStartEvent(false, error.getMessage()));
			}
		});
	}
	
	@Override
	public void stopService() {
		logger.info("Stoping sms service");
		ServiceLifecycleRequest request = requestFactory.getMessageServiceRequest();
		request.stopService().fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				view.notifyServiceStoped();
				
				/**
				 * Ini sama juga dengan alasan di atas kenapa di fire, global notification.
				 */
				eventBus.fireEvent(new SmsServiceStopEvent());
			}
			@Override
			public void onFailure(ServerFailure error) {
				view.notifyServiceStopingFailed(new RuntimeException(error.getMessage()));
				
				/**
				 * Di-fire untuk global notification.
				 */
				eventBus.fireEvent(new SmsServiceStopEvent(false, error.getMessage()));
			}
		});
	}
	
	private void configureHandler() {
		eventBus.addHandler(SmsServiceStartReqEvent.TYPE, new SmsServiceStartReqEvent.Handler() {
			@Override
			public void onSmsServiceStartReq(SmsServiceStartReqEvent event) {
				startService();
			}
		});
		eventBus.addHandler(SmsServiceStopReqEvent.TYPE, new SmsServiceStopReqEvent.Handler() {
			@Override
			public void onSmsServiceStopReq(SmsServiceStopReqEvent event) {
				stopService();
			}
		});
		
		eventBus.addHandler(AddGatewayRequestEvent.TYPE, new AddGatewayRequestEvent.Handler() {
			@Override
			public void onAddGatewayRequest(AddGatewayRequestEvent event) {
				Info.display("Info", "Add gateway requested. Pertama do appropriate cheking for request.");
				
				if(event.getType() == SupportedGatewayType.SERIAL_MODEM) {
					logger.info("Create serial gateway info proxy.");
					SerialGatewayManagementRequest requestContext = requestFactory.getSerialGatewayManagementRequest();
					SerialGatewayInfoProxy gatewayProxy = requestContext.create(SerialGatewayInfoProxy.class);
					logger.info("Serial gateway info created : " + gatewayProxy);
					
					view.startCreateGateway(gatewayProxy, requestContext);
				}
			}
		});
	}
	
	private View bindView(View view) {
		return view;
	}
}
