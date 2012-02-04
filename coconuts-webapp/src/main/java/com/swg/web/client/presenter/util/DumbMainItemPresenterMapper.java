package com.swg.web.client.presenter.util;

import java.util.Map;

import com.google.inject.Inject;
import com.swg.web.client.ioc.ClientFactory;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.impl.DashBoardPresenter;
import com.swg.web.client.presenter.impl.InboundMessagePresenter;
import com.swg.web.client.presenter.impl.OutboundMessagePresenter;

/**
 * Mapper untuk main item presenter. Disebut dumb karena mapping dilakukan secara manual.
 * Mungkin versi smart-nya bisa diimplementasi dengan defered binding.
 * 
 * @author zakyalvan
 */
public class DumbMainItemPresenterMapper extends MainItemPresenterMapper {
	@Inject
	private ClientFactory clientFactory;
	
	@Override
	protected void configurePresenterMap(Map<String, MainItemPresenter<?>> map) {
		map.put(DashBoardPresenter.NAME, clientFactory.getDashBoardPresenter());
		map.put(InboundMessagePresenter.NAME, clientFactory.getInboundMessagePresenter());
		map.put(OutboundMessagePresenter.NAME, clientFactory.getOutboundMessagePresenter());
	}
}
