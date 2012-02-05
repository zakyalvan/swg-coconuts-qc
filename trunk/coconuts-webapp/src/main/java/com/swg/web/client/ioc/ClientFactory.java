package com.swg.web.client.ioc;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.RequestTransport;
import com.swg.web.client.CoconutsApplication;
import com.swg.web.client.CoconutsShell;
import com.swg.web.client.activity.MainActivity;
import com.swg.web.client.activity.SmsServiceSettingActivity;
import com.swg.web.client.activity.VoteObserverActivity;
import com.swg.web.client.presenter.MainPresenter.MainView;
import com.swg.web.client.presenter.SmsServicePresenter;
import com.swg.web.client.presenter.SmsServicePresenter.View;
import com.swg.web.client.presenter.SmsServiceSettingPresenter;
import com.swg.web.client.presenter.impl.DashBoardPresenter;
import com.swg.web.client.presenter.impl.DashBoardPresenter.DashBoardView;
import com.swg.web.client.presenter.impl.InboundMessagePresenter;
import com.swg.web.client.presenter.impl.InboundMessagePresenter.InboundMessageView;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter.OutboundMessageView;
import com.swg.web.client.presenter.impl.VoteCountingPresenter;
import com.swg.web.client.presenter.impl.VoteCountingPresenter.VoteCountingView;
import com.swg.web.client.presenter.util.MainItemPresenterMapper;
import com.swg.web.client.view.GlobalWidgetTimer;
import com.swg.web.client.view.web.VoteObserverWidget;
import com.swg.web.shared.request.BaseRequestFactory;
import com.swg.web.shared.request.MessagingRequestFactory;
import com.swg.web.shared.request.SecurityRequestFactory;
import com.swg.web.shared.request.VoteObserverRequestFactory;

/**
 * Kelas ini merupakan implementasi Ginjector untuk aplikasi ini.
 * Ginjector merupakan factory object yang sekalian bisa digunakan
 * untuk dependency injection di sisi client berbasis gwt.
 * 
 * @author zakyalvan
 */
@GinModules(CoconutsModule.class)
public interface ClientFactory extends Ginjector {
	CoconutsApplication getApplication();
	CoconutsShell getShell();
	
	ActivityMapper getActivityMapper();
	
	GlobalWidgetTimer getGlobalWidgetTimer();
	
	MainView getMainView();
	MainActivity getMainActivity();
	
	MainItemPresenterMapper getMainItemPresenterMapper();
	
	EventBus getEventBus();
	PlaceController getPlaceController();
	
	RequestTransport getRequestTransport();
	
	DashBoardView getDashBoardView();
	DashBoardPresenter getDashBoardPresenter();
	
	InboundMessageView getInboundMessageView();
	InboundMessagePresenter getInboundMessagePresenter();
	
	OutboundMessageView getOutboundMessageView();
	OutboundMessagePresenter getOutboundMessagePresenter();
	
	VoteCountingView getVoteCountingView();
	VoteCountingPresenter getVoteCountingPresenter();
	
	/**
	 * Retrieve initialized base request factory.
	 * 
	 * @return {@link BaseRequestFactory}
	 */
	BaseRequestFactory getBaseRequestFactory();
	
	/**
	 * Retrieve intialized vote observer request factory.
	 * 
	 * @return {@link VoteObserverRequestFactory}
	 */
	VoteObserverRequestFactory getVoteObserverRequestFactory();
	
	/**
	 * Retrieve initialized {@link SecurityRequestFactory}.
	 * 
	 * @return {@link SecurityRequestFactory}
	 */
	SecurityRequestFactory getSecurityRequestFactory();
	
	/**
	 * Retrieve initialized {@link MessagingRequestFactory}.
	 * 
	 * @return {@link MessagingRequestFactory}
	 */
	MessagingRequestFactory getMessagingRequestFactory();
		
	VoteObserverWidget getVoteObserverMngmntView();
	VoteObserverActivity getVoteObserverMngmntActivity();
	
	/**
	 * Retrieve view untuk manajemen service perpesanan.
	 * 
	 * @return {@link View}
	 */
	SmsServicePresenter.View getMessagingServiceView();
	
	
	SmsServiceSettingPresenter.View getSmsServiceSettingView();
	SmsServiceSettingActivity getSmsServiceSettingActivity();
}