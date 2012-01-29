package com.swg.sms.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@DiscriminatorValue(GatewayInfo.SERIAL_GATEWAY_TYPE)
@Table(name="serial_gateway_info")
public class SerialGatewayInfo extends GatewayInfo {
	private static final long serialVersionUID = -2936939841840049467L;

	@NotBlank
	@Column(length=10)
	private String serialPort;
	
	@NotNull
	private Integer baudRate;
	
	@NotBlank
	private String manufacturer;
	
	@NotBlank
	private String modelNumber;
	
	public SerialGatewayInfo() {}
	public SerialGatewayInfo(String id, String serialPort, Integer baudRate, String manufacturer, String modelNumber) {
		this.id = id;
		this.serialPort = serialPort;
		this.baudRate = baudRate;
		this.manufacturer = manufacturer;
		this.modelNumber = modelNumber;
	}

	public String getSerialPort() {
		return serialPort;
	}
	public void setSerialPort(String serialPort) {
		this.serialPort = serialPort;
	}
	
	public Integer getBaudRate() {
		return baudRate;
	}
	public void setBaudRate(Integer baudRate) {
		this.baudRate = baudRate;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
}
