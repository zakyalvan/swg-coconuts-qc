package com.swg.web.client.presenter.impl;

import java.util.List;

import com.google.inject.Inject;
import com.swg.web.client.presenter.MainItemPresenter;
import com.swg.web.client.presenter.MainItemPresenter.MainItemView;
import com.swg.web.client.presenter.impl.VoteCountingPresenter.VoteCountingView;
import com.swg.web.shared.proxy.VoteResultProxy;

/**
 * 
 * 
 * @author zakyalvan
 */
public class VoteCountingPresenter implements MainItemPresenter<VoteCountingView> {
	private static final long serialVersionUID = 574208082603513382L;

	public static final String NAME = "vote-counting";
	
	@Inject
	private VoteCountingView voteCountingView;
	
	private boolean interactive = false;
	
	@Override
	public VoteCountingView getView() {
		return voteCountingView;
	}
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public boolean isInteractive() {
		return interactive;
	}
	@Override
	public void setInteractive(boolean interactive) {
		this.interactive = interactive;
	}

	public interface VoteCountingView extends MainItemView {
		/**
		 * Retrieve record yang dipilih oleh user.
		 * Berguna misalnya untuk pada saat pemilihan data untuk dihapus dll.
		 * 
		 * @return List<VoteCountProxy>
		 */
		List<VoteResultProxy> getSelectedRecords();
		
		/**
		 * Tambahkan data baru ke dalam data.
		 * Misalnya data yang direquest dari server adalah data baru dengan offset tertentu.
		 * 
		 * @param List<VoteCountProxy> datas
		 */
		void appendDatas(List<VoteResultProxy> datas);
		
		/**
		 * Set data untuk ditampilkan. Ini berarti mereplace data lama dalam view.
		 * 
		 * @param List<VoteCountProxy> datas
		 */
		void setDatas(List<VoteResultProxy> datas);
	}
}