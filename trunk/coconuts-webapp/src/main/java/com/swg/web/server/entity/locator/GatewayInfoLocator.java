package com.swg.web.server.entity.locator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.swg.sms.entity.GatewayInfo;
import com.swg.sms.entity.SerialGatewayInfo;
import com.swg.sms.entity.repo.GatewayInfoRepository;

@Component
@Scope("prototype")
@Configurable(dependencyCheck=true)
public class GatewayInfoLocator extends Locator<GatewayInfo, String> {
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired(required=true)
	private GatewayInfoRepository repository;
	
	@Override
	public GatewayInfo create(Class<? extends GatewayInfo> clazz) {
		logger.info("Create fresh gateway info object with type : " + clazz);
		GatewayInfo gatewayInfo = null;
		if(clazz.isAssignableFrom(SerialGatewayInfo.class)) {
			gatewayInfo = new SerialGatewayInfo();
		}
		
		if(gatewayInfo == null)
			logger.info("Can't create gateway info object for type requested.");
		
		return gatewayInfo;
	}

	@Override
	public GatewayInfo find(Class<? extends GatewayInfo> clazz, String id) {
		return repository.findOne(id);
	}

	@Override
	public Class<GatewayInfo> getDomainType() {
		return GatewayInfo.class;
	}

	@Override
	public String getId(GatewayInfo domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(GatewayInfo domainObject) {
		return domainObject.getVersion();
	}
}
