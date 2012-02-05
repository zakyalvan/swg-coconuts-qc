package com.swg.web.server.entity.locator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.swg.sms.entity.InboundMessageBean;
import com.swg.sms.entity.repo.InboundMessageRepository;

/**
 * Locator untuk entity inbound message.
 * Kelas ini gwt-related, tepatnya digunakan dalam fitur request factory.
 * 
 * @author zakyalvan
 */
@Component
@Scope("prototype")
@Configurable
public class InboundMessageLocator extends Locator<InboundMessageBean, Integer> {
	@Autowired(required=true)
	private InboundMessageRepository inboundMessageRepository;
	
	@Override
	public InboundMessageBean create(Class<? extends InboundMessageBean> clazz) {
		return new InboundMessageBean();
	}

	@Override
	public InboundMessageBean find(Class<? extends InboundMessageBean> clazz, Integer id) {
		return inboundMessageRepository.findOne(id);
	}

	@Override
	public Class<InboundMessageBean> getDomainType() {
		return InboundMessageBean.class;
	}

	@Override
	public Integer getId(InboundMessageBean domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<Integer> getIdType() {
		return Integer.class;
	}

	@Override
	public Object getVersion(InboundMessageBean domainObject) {
		return domainObject.getVersion();
	}
}