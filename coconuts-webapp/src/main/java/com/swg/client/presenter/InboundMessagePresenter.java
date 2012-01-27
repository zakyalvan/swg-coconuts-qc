package com.swg.client.presenter;

import com.swg.client.presenter.InboundMessagePresenter.View;
import com.swg.client.view.TabableView;

/**
 * Presenter yang berhubungan dengan aktivitas management pesan masuk.
 * 
 * @author zakyalvan
 */
public interface InboundMessagePresenter extends Presenter<View> {
	public interface View extends TabableView {
		
	}
}
