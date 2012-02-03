package com.swg.web.client.presenter.impl;

import com.google.inject.Inject;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.DashBoardPresenter.DashBoardView;

public class DashBoardPresenter implements MainItemPresenter<DashBoardView> {
	public interface DashBoardView extends MainItemView {
		
	}

	private static final long serialVersionUID = -1268703918051427626L;
	public static final String NAME = "dashboard";
	
	private DashBoardView view;
	
	@Inject
	public DashBoardPresenter(DashBoardView view) {
		this.view = view;
	}
	
	@Override
	public DashBoardView getView() {
		return view;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
