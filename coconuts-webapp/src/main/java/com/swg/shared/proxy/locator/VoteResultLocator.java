package com.swg.shared.proxy.locator;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.swg.core.entity.VoteResult;

@Component
@Scope("prototype")
@Configurable(dependencyCheck=true)
public class VoteResultLocator extends Locator<VoteResult, Integer> {
	@Override
	public VoteResult create(Class<? extends VoteResult> clazz) {
		return new VoteResult();
	}

	@Override
	public VoteResult find(Class<? extends VoteResult> clazz, Integer id) {
		return null;
	}

	@Override
	public Class<VoteResult> getDomainType() {
		return VoteResult.class;
	}

	@Override
	public Integer getId(VoteResult domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<Integer> getIdType() {
		return Integer.class;
	}

	@Override
	public Object getVersion(VoteResult domainObject) {
		return domainObject.getVersion();
	}
}
