package com.swg.server.sms.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Implementasi default untuk pesan masuk.
 * Object inilah yang disimpan dalam basis data.
 * 
 * @author zakyalvan
 */
@Entity(name="SetanAlas")
@Table(name="inbound_message")
public class InboundMessageBean implements InboundMessage {
	private static final long serialVersionUID = -1393692240111385797L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String content;
	
	@NotBlank
	private String sender;

	private String serviceCenter;

	@ManyToOne
	private GatewayInfo gatewayInfo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveDate;
	
	@Version
	private Timestamp version;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;
	
	/**
	 * Default konstruktor, diperlukan oleh engine jpa.
	 */
	InboundMessageBean() {}
	public InboundMessageBean(String content, String sender,  GatewayInfo gatewayInfo, String serviceCenter, Date receiveDate, Status status) {
		this.content = content;
		this.sender = sender;
		this.gatewayInfo = gatewayInfo;
		this.serviceCenter = serviceCenter;
		this.receiveDate = receiveDate;
		this.status = status;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public String getSender() {
		return sender;
	}

	public String getServiceCenter() {
		return serviceCenter;
	}
	public void setServiceCenter(String serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
	
	public GatewayInfo getGatewayInfo() {
		return gatewayInfo;
	}
	public void setGatewayInfo(GatewayInfo gatewayInfo) {
		this.gatewayInfo = gatewayInfo;
	}
	
	@Override
	public Date getReceiveDate() {
		return receiveDate;
	}

	@Override
	public Date getVersion() {
		return version;
	}

	@Override
	public Status getStatus() {
		return status;
	}
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "InboundMessageBean [id=" + id + ", content=" + content
				+ ", sender=" + sender + ", serviceCenter=" + serviceCenter
				+ ", gatewayInfo=" + gatewayInfo + ", receiveDate="
				+ receiveDate + ", version=" + version + ", status=" + status
				+ "]";
	}
}