package com.swg.client.activity;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.swg.client.event.VoteCountDeleteReqEvent;
import com.swg.client.event.VoteCountRequestEvent;
import com.swg.client.event.VoteCountRequestEvent.Handler;
import com.swg.client.presenter.VoteCountingPresenter;

/**
 * Aktivitas dalam place perhitungan hasil pemungutan atau perolehan suara.
 * 
 * @author zakyalvan
 */
public class VoteCountingActivity extends AbstractActivity implements VoteCountingPresenter {
	private Logger logger = Logger.getLogger("VoteCountingActivity");
	
	private EventBus eventBus;
	private VoteCountingPresenter.View view;
	
	@Inject(optional=false)
	public VoteCountingActivity(EventBus eventBus, VoteCountingPresenter.View view) {
		this.eventBus = eventBus;
		this.view = view;
		
		configureHandler();
	}
	
	private void configureHandler() {
		logger.info("Configure handler.");
		Handler requestHandler = new VoteCountRequestEvent.Handler() {
			@Override
			public void onVoteCountRequest(VoteCountRequestEvent event) {
				logger.info("Execute load request event.");
			}
		};
		eventBus.addHandler(VoteCountRequestEvent.TYPE, requestHandler);
		
		eventBus.addHandler(VoteCountDeleteReqEvent.TYPE, new VoteCountDeleteReqEvent.Handler() {
			@Override
			public void onVoteCountDeleteRequest(VoteCountDeleteReqEvent event) {
				logger.info("Execute delete request event.");
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		logger.info("Start activity. Attaching view objet : " + view);
		panel.setWidget(view);
	}

	@Override
	public View getView() {
		return view;
	}
}