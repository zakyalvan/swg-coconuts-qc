package com.swg.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.swg.core.entity.VoteObserver;
import com.swg.core.entity.repo.VoteObserverRepository;

/**
 * Implementasi default dari {@link VoteObserverManager}
 * 
 * @author zakyalvan
 */
@Service
public class VoteObserverManagerImpl implements VoteObserverManager {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired(required=true)
	private VoteObserverRepository repository;
	
	@Override
	public void delete(VoteObserver entity) {
		List<VoteObserver> willBeDeleted = new ArrayList<VoteObserver>();
		willBeDeleted.add(entity);
		delete(willBeDeleted);
	}

	@Override
	public void delete(List<VoteObserver> entities) {
		logger.debug("Hapus data pemantau atau saksi. Sebenarnya tidak menghapus data secara fisik dari basis data.");
		for(VoteObserver entity : entities) {
			entity.setDeleted(true);
		}
		repository.save(entities);
	}

	@Override
	public Long countVoteObservers() {
		logger.debug("Count pemantau atau saksi pemungutan suara yang tercatat.");
		return repository.count();
	}

	@Override
	public List<VoteObserver> listVoteObservers() {
		logger.debug("List seluruh pemantau atau saksi pemungutan suara yang tercatat.");
		return new ArrayList<VoteObserver>(repository.findAll());
	}

	@Override
	public VoteObserverPagedList listVoteObservers(Integer offset, Integer limit) {
		logger.debug("List pemantau atau saksi pemungutan suara yang tercatat mulai dari record #offset sebanyak #limit record.");
		
		Integer page = (int) Math.ceil(offset/limit);
		Page<VoteObserver> paged = repository.findAll(new PageRequest(page, limit));
		
		return new VoteObserverPagedList(paged.getContent(), 
				paged.getNumber(), 
				paged.getSize(), 
				paged.getTotalElements());
	}	
}
