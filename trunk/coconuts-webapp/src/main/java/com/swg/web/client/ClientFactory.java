package com.swg.web.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.RequestTransport;
import com.swg.web.client.activity.DashBoardActivity;
import com.swg.web.client.activity.SmsServiceActivity;
import com.swg.web.client.activity.SmsServiceSettingActivity;
import com.swg.web.client.activity.VoteCountingActivity;
import com.swg.web.client.activity.VoteObserverActivity;
import com.swg.web.client.presenter.DashBoardPresenter;
import com.swg.web.client.presenter.SmsServicePresenter;
import com.swg.web.client.presenter.SmsServiceSettingPresenter;
import com.swg.web.client.presenter.VoteCountingPresenter;
import com.swg.web.client.presenter.SmsServicePresenter.View;
import com.swg.web.client.view.widget.SerialGatewayEditorWidget;
import com.swg.web.client.view.widget.VoteObserverWidget;
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
@GinModules(QuickCountModule.class)
public interface ClientFactory extends Ginjector {
	EventBus getEventBus();
	PlaceController getPlaceController();
	
	RequestTransport getRequestTransport();
	
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
	
	QuickCountShell getApplicationShell();
	
	
	DashBoardPresenter.View getDashBoardView();
	DashBoardActivity getDashBoardActivity();
	
	VoteObserverWidget getVoteObserverMngmntView();
	VoteObserverActivity getVoteObserverMngmntActivity();	
	
	VoteCountingPresenter.View getVoteCountingView();
	VoteCountingActivity getVoteCountingActivity();
	
	/**
	 * Retrieve view untuk manajemen service perpesanan.
	 * 
	 * @return {@link View}
	 */
	SmsServicePresenter.View getMessagingServiceView();
	
	/**
	 * Retrieve view untuk manajemen service perpesanan.
	 * 
	 * @return {@link SmsServiceActivity}
	 */
	SmsServiceActivity getMessagingMngmntActivity();
	
	SerialGatewayEditorWidget getSerialGatewayEditorWidget();
	
	SmsServiceSettingPresenter.View getSmsServiceSettingView();
	SmsServiceSettingActivity getSmsServiceSettingActivity();
}