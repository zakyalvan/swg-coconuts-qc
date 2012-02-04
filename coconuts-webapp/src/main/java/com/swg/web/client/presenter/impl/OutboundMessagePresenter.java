package com.swg.web.client.presenter.impl;

import com.google.inject.Inject;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter.OutboundMessageView;

/**
 * Presenter untuk nampilin data pesan keluar yang sudah pernah dikirim
 * beserta atribute lainnya.
 * 
 * @author zakyalvan
 */
public class OutboundMessagePresenter implements MainItemPresenter<OutboundMessageView> {
	private static final long serialVersionUID = 3195192944784699804L;

	public static final String NAME = "outbound-message";
	
	@Inject
	private OutboundMessageView view;
	
	@Override
	public OutboundMessageView getView() {
		return view;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	public interface OutboundMessageView extends MainItemView {
		
	}

	@Override
	public boolean isInteractive() {
		return false;
	}
	@Override
	public void setIteractive(boolean interactive) {
		
	}
}
