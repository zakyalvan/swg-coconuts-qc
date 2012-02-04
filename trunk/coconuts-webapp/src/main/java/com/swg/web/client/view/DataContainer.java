package com.swg.web.client.view;

import java.util.List;

import com.sencha.gxt.data.shared.ListStore;

/**
 * Kontrak yang digunakan widget yang merupakan kontainer data (Misalnya yang memiliki {@link ListStore}).
 * 
 * @author zakyalvan
 */
public interface DataContainer<T, V> {
	/**
	 * Apakah kontent dari data kontainer ini di reload automatis.
	 * 
	 * @return boolean
	 */
	boolean isAutoreloadEnabled();
	
	/**
	 * Apakah proses reload secara default partial atau reload seluruh data.
	 * Berkaitan dengan {@link #getLastDataVersion()}, jadi yang diload adalah
	 * data dengan version lebih besar atau setelah {@link #getLastDataVersion()}.
	 * 
	 * @return boolean
	 */
	boolean isAutoreloadPartial();
	
	/**
	 * Retrieve versi terakhir dari data dalam kontainer ini.
	 * Jika versi terakhir null, maka (seharusnya) automatcally yang direload 
	 * adalah seluruh data walaupun secara explisit {@link #isAutoreloadPartial()} bernilai true.
	 * 
	 * @return V (version type).
	 */
	V getLastDataVersion();
	
	/**
	 * Set data untuk data container ini. Method ini secara implisit set data secara keseluruhan (!partial)
	 * 
	 * @param List<T> datas
	 */
	void setDatas(List<T> datas);
	
	/**
	 * Set data untuk data container ini.
	 * 
	 * @param List<T> datas
	 * @param boolean partial
	 */
	void setDatas(List<T> datas, boolean partial);
	
	void clearDatas();
}