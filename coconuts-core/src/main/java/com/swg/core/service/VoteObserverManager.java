package com.swg.core.service;

import java.util.List;

import com.swg.core.entity.VoteObserverBean;

/**
 * Kontrak untuk service atau manager pemantau/saksi pemungutan suara.
 * 
 * @author zakyalvan
 */
public interface VoteObserverManager {
	/**
	 * Hapus satu entity vote observer.
	 * Seharusnya tidak menghapus data secara fisik dari basis data.
	 * 
	 * @param {@link VoteObserverBean} entity
	 */
	void delete(VoteObserverBean entity);
	
	/**
	 * Hapus beberapa entity pemantau atau saksi.
	 * Seharusnya tidak menghapus data secara fisik dari basis data system.
	 * 
	 * @param entities
	 */
	void delete(List<VoteObserverBean> entities);
	
	/**
	 * Hitung jumlah pemantau/saksi pemungutan suara yang tercatat dalam basis data system.
	 * 
	 * @return {@link Integer}
	 */
	Long countVoteObservers();
	
	/**
	 * List seluruh pemantau/saksi pemungutan suara yang tercatat dalam basis data system.
	 * 
	 * @return
	 */
	List<VoteObserverBean> listVoteObservers();
	
	/**
	 * List pemantau/saksi pemungutan suara yang tercatat dalam basis data system
	 * mulai dari record ke #offset sebanyak #limit orang.
	 * 
	 * @param Integer offset
	 * @param Integer limit
	 * @return {@link List<VoteObserver>}
	 */
	VoteObserverPagedList listVoteObservers(Integer offset, Integer limit);
}
