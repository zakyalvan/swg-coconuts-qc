package com.swg.web.shared.proxy.locator;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.swg.sms.entity.InboundMessage;
import com.swg.sms.entity.InboundMessageBean;

/**
 * Locator untuk entity inbound message.
 * Kelas ini gwt-related, tepatnya digunakan dalam fitur request factory.
 * 
 * @author zakyalvan
 */
public class InboundMessageLocator extends Locator<InboundMessage, Integer> {
	@Override
	public InboundMessageBean create(Class<? extends InboundMessage> clazz) {
		return null;
	}

	@Override
	public InboundMessageBean find(Class<? extends InboundMessage> clazz, Integer id) {
		return null;
	}

	@Override
	public Class<InboundMessage> getDomainType() {
		return InboundMessage.class;
	}

	@Override
	public Integer getId(InboundMessage domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<Integer> getIdType() {
		return Integer.class;
	}

	@Override
	public Object getVersion(InboundMessage domainObject) {
		return domainObject.getVersion();
	}
}