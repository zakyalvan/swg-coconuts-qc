package com.swg.server.sms.service;

import org.smslib.AGateway;

import com.swg.server.sms.entity.GatewayInfo;

/**
 * Builder untuk gateway yang sesungguhnya, 
 * yaitu gateway yang dipeke oleh engine back-end 
 * (secara default{@link AGateway} dari smslib).
 *  
 * @author zakyalvan
 */
public interface GatewayFactory<T extends AGateway> {
	public class CantCreateGatewayException extends RuntimeException {
		private static final long serialVersionUID = 3163115181141311166L;
		
		public static final Integer FACTORY_CANT_HANDLE = 1;
		public static final Integer INVALID_REQUIRED_PARAM = 2;
		public static final Integer OTHER = 3;
		
		private Integer code = FACTORY_CANT_HANDLE;
		private GatewayInfo gatewayInfo;
		
		public CantCreateGatewayException(String message, GatewayInfo gatewayInfo) {
			super(message);
			this.gatewayInfo = gatewayInfo;
		}
		public CantCreateGatewayException(String message, GatewayInfo gatewayInfo, Integer code) {
			super(message);
			this.gatewayInfo = gatewayInfo;
			this.code = code;
		}

		public GatewayInfo getGatewayInfo() {
			return gatewayInfo;
		}
		public Integer getCode() {
			return code;
		}
	}
 	
	/**
	 * Create gateway berdasarkan info dari gateway.
	 * 
	 * @param {@link GatewayInfo} gatewayInfo
	 * @return
	 */
	T createGateway(GatewayInfo gatewayInfo) throws CantCreateGatewayException;
}