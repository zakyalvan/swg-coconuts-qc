package com.swg.web.server.entity.locator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.swg.core.entity.VoteObserverBean;
import com.swg.core.entity.repo.VoteObserverRepository;

/**
 * Locator untuk entity object VoteObserver.
 * Object ini gwt-related dan digunakan dalam fitur request factory.
 * 
 * @author zakyalvan
 */
@Component
@Configurable(dependencyCheck=true)
@Scope("prototype")
public class VoteObserverLocator extends Locator<VoteObserverBean, Integer> {
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired(required=true)
	private VoteObserverRepository repository;
	
	@Override
	public VoteObserverBean create(Class<? extends VoteObserverBean> clazz) {
		logger.debug("Create new observer.");
		return new VoteObserverBean();
	}
	@Override
	public VoteObserverBean find(Class<? extends VoteObserverBean> clazz, Integer id) {
		logger.debug("");
		return repository.findOne(id);
	}
	@Override
	public Class<VoteObserverBean> getDomainType() {
		return VoteObserverBean.class;
	}
	@Override
	public Integer getId(VoteObserverBean domainObject) {
		return domainObject.getId();
	}
	@Override
	public Class<Integer> getIdType() {
		return Integer.class;
	}
	@Override
	public Object getVersion(VoteObserverBean domainObject) {
		return domainObject.getVersion();
	}
}
