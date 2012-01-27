package com.swg.shared.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.swg.server.sms.entity.SerialGatewayInfo;
import com.swg.shared.proxy.locator.GatewayInfoLocator;

@ProxyFor(value=SerialGatewayInfo.class, locator=GatewayInfoLocator.class)
public interface SerialGatewayInfoProxy extends GatewayInfoProxy {
	String getId();
	void setId(String id);
	
	String getSerialPort();
	void setSerialPort(String serialPort);
	
	Integer getBaudRate();
	void setBaudRate(Integer baudRate);
	
	String getManufacturer();
	void setManufacturer(String manufacturer);
	
	String getModelNumber();
	void setModelNumber(String modelNumber);
}
