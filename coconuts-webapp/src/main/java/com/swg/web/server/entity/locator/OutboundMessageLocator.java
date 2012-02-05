package com.swg.web.server.entity.locator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.swg.sms.entity.OutboundMessageBean;
import com.swg.sms.entity.repo.OutboundMessageRepository;

@Component
@Configurable
@Scope("prototype")
public class OutboundMessageLocator extends Locator<OutboundMessageBean, Integer> {
	@Autowired(required=true)
	private OutboundMessageRepository outboundMessageRepository;
	
	@Override
	public OutboundMessageBean create(Class<? extends OutboundMessageBean> clazz) {
		return new OutboundMessageBean();
	}

	@Override
	public OutboundMessageBean find(Class<? extends OutboundMessageBean> clazz, Integer id) {
		return outboundMessageRepository.findOne(id);
	}

	@Override
	public Class<OutboundMessageBean> getDomainType() {
		return OutboundMessageBean.class;
	}

	@Override
	public Integer getId(OutboundMessageBean domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<Integer> getIdType() {
		return Integer.class;
	}

	@Override
	public Object getVersion(OutboundMessageBean domainObject) {
		return domainObject.getVersion();
	}
}
