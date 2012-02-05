package com.swg.web.client.presenter;

import com.swg.web.client.activity.MainActivity;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.util.NamedObject;
import com.swg.web.client.view.TabableView;

public interface MainItemPresenter<V extends MainItemView> extends Presenter<V>, NamedObject {
	public static interface MainItemView extends TabableView {
		
	}
	
	/**
	 * Flag yang nunjukin presenter ini sedang interactive atau tidak (idle).
	 * Dapat digunakan untuk mengenable timer atau scheduler (misalnya untuk autoreload data).
	 * Nilai ini akan diset di {@link MainActivity} object berdasarkan lifecycle dari object tersebut.
	 * 
	 * @return
	 */
	boolean isInteractive();
	void setInteractive(boolean interactive);
}
