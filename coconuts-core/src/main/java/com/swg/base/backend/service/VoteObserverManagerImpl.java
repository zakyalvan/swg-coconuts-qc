package com.swg.base.backend.service;

import com.swg.base.backend.entity.VoteObserverBean;
import com.swg.base.backend.repository.VoteObserverRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementasi default dari {@link VoteObserverManager}
 *
 * @author zakyalvan
 */
@Service
public class VoteObserverManagerImpl implements VoteObserverManager {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired(required = false)
    private VoteObserverRepository repository;

    @Override
    public void delete(VoteObserverBean entity) {
        List<VoteObserverBean> willBeDeleted = new ArrayList<VoteObserverBean>();
        willBeDeleted.add(entity);
        delete(willBeDeleted);
    }

    @Override
    public void delete(List<VoteObserverBean> entities) {
        logger.debug("Hapus data pemantau atau saksi. Sebenarnya tidak menghapus data secara fisik dari basis data.");
        for (VoteObserverBean entity : entities) {
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
    public List<VoteObserverBean> listVoteObservers() {
        logger.debug("List seluruh pemantau atau saksi pemungutan suara yang tercatat.");
        return new ArrayList<VoteObserverBean>(repository.findAll());
    }

    @Override
    public VoteObserverPagedList listVoteObservers(Integer offset, Integer limit) {
        logger.debug("List pemantau atau saksi pemungutan suara yang tercatat mulai dari record #offset sebanyak #limit record.");

        Integer page = (int) Math.ceil(offset / limit);
        Page<VoteObserverBean> paged = repository.findAll(new PageRequest(page, limit));

        return new VoteObserverPagedList(paged.getContent(),
                paged.getNumber(),
                paged.getSize(),
                paged.getTotalElements());
    }
}
