package com.swg.web.client.presenter;

import java.util.List;

import com.swg.web.client.presenter.VoteCountingPresenter.View;
import com.swg.web.client.view.TabableView;
import com.swg.web.shared.proxy.VoteResultProxy;

/**
 * Kontrak untuk presenter yang berhubungan presentasi 
 * data hasil perhitungan perolehan suara.
 * 
 * @author zakyalvan
 */
public interface VoteCountingPresenter extends Presenter<View> {
	public interface View extends TabableView {
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