package com.swg.client.presenter;

import com.swg.client.presenter.SmsServiceSettingPresenter.View;
import com.swg.client.view.WindowableView;

public interface SmsServiceSettingPresenter extends Presenter<View> {
	public interface View extends WindowableView {
		public static final String UPDATE_SETTING_BTN_ID = "update-setting";
		public static final String CANCEL_SETTING_BTN_ID = "cancel_setting";
	}
	
	void updateServiceSetting();
}
