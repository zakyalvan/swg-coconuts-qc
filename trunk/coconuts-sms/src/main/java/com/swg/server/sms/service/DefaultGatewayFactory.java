package com.swg.server.sms.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.swg.server.sms.entity.GatewayInfo;
import com.swg.server.sms.entity.SerialGatewayInfo;

/**
 * Implementasi default dari {@link GatewayFactory},
 * object yang bertugas men-create gateway object yang digunakan oleh service.
 * 
 * @author zakyalvan
 */
@Component
public class DefaultGatewayFactory implements GatewayFactory<AGateway>, InitializingBean {
	private Logger logger = Logger.getLogger(getClass());
	private Set<GatewayFactory<? extends AGateway>> delegates = new HashSet<GatewayFactory<? extends AGateway>>();

	@Override
	public AGateway createGateway(GatewayInfo gatewayInfo) throws CantCreateGatewayException {
		logger.debug("Create gateway using info : " + gatewayInfo);
		
		assert (gatewayInfo != null) : "Parameter gateway info tidak boleh null.";
		
		AGateway gateway = null;
		for(GatewayFactory<? extends AGateway> builder : delegates) {
			logger.info("Coba create pake factory : " + builder);
			try {
				gateway = builder.createGateway(gatewayInfo);
			}
			catch(CantCreateGatewayException e) {
				logger.error("Terjadi kesalahan dalam create gateway : " + e.getMessage());
				e.printStackTrace();
				
				if(e.getCode() != CantCreateGatewayException.FACTORY_CANT_HANDLE) {
					throw e;
				}
			}
		}
		
		if(gateway == null) {
			throw new CantCreateGatewayException(
				"No delegate factory can handle request", 
				gatewayInfo, 
				CantCreateGatewayException.FACTORY_CANT_HANDLE
			);
		}
		return gateway;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("Initialize delegated factory.");
		delegates.add(new SerialGatewayFactory());
	}
	
	public static class SerialGatewayFactory extends AbstractGatewayFactory<SerialModemGateway, SerialGatewayInfo> {
		public SerialGatewayFactory() {
			super(SerialGatewayInfo.class);
		}

		@Override
		protected SerialModemGateway doCreateGateway(SerialGatewayInfo gatewayInfo) throws CantCreateGatewayException {
			SerialModemGateway gateway = new SerialModemGateway(
					gatewayInfo.getId(),
					gatewayInfo.getSerialPort(), 
					gatewayInfo.getBaudRate(), 
					gatewayInfo.getManufacturer(), 
					gatewayInfo.getModelNumber());
			return gateway;
		}
	}
	
	public static abstract class AbstractGatewayFactory<G extends AGateway, I extends GatewayInfo> implements GatewayFactory<G> {
		protected Class<I> expectedInfoType;
		
		public AbstractGatewayFactory(Class<I> expectedInfoType) {
			this.expectedInfoType = expectedInfoType;
		}

		@Override
		public G createGateway(GatewayInfo gatewayInfo) throws CantCreateGatewayException {
			if(gatewayInfo == null) {
				throw new CantCreateGatewayException(
						"Parameter gateway info tidak boleh null.", 
						gatewayInfo, 
						CantCreateGatewayException.INVALID_REQUIRED_PARAM);
			}
			if(!expectedInfoType.isAssignableFrom(gatewayInfo.getClass())) {
				throw new CantCreateGatewayException(
						"Invalid gateway info parameter. Diharapkan " + expectedInfoType + ", diberikan " + gatewayInfo.getClass(), 
						gatewayInfo);
			}
			G gateway = doCreateGateway(expectedInfoType.cast(gatewayInfo));
			
			if(gatewayInfo.getCapability() == GatewayInfo.ALL_CAPABILITY) {
				gateway.setInbound(true);
				gateway.setOutbound(false);
			}
			else if(gatewayInfo.getCapability() == GatewayInfo.INBOUND_CAPABILITY) {
				gateway.setInbound(true);
				gateway.setOutbound(false);
			}
			else if(gatewayInfo.getCapability() == GatewayInfo.OUTBOUND_CAPABILITY) {
				gateway.setInbound(false);
				gateway.setOutbound(true);
			}
			else {
				gateway.setInbound(false);
				gateway.setOutbound(false);
			}
			
			/**
			 * Jika gateway tidak dienable, maka tidak dapat digunakan untuk mengirim atau menerima.
			 */
			if(!gatewayInfo.isEnabled()) {
				gateway.setInbound(false);
				gateway.setOutbound(false);
			}
			return gateway;
		}
		
		protected abstract G doCreateGateway(I gatewayInfo) throws CantCreateGatewayException;
	}
}