package com.swg.sms.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="inbound_message")
public class InboundMessageBean implements InboundMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="gateway_info_id")
	private GatewayInfo gatewayInfo;
	
	@NotBlank
	@Column(name="sender", length=14)
	private String sender;
	
	@Column(name="service_center", length=14)
	private String serviceCenter;
	
	@NotBlank
	@Column(name="content")
	private String content;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="receive_data")
	private Date receiveDate;
	
	@NotNull
	@Column(name="status")
	private Integer status;
	
	@Version
	@Column(name="version")
	private Timestamp version;
	
	public InboundMessageBean() {}
	public InboundMessageBean(String sender, String content) {
		this.sender = sender;
		this.content = content;
		this.status = InboundMessage.NEW_MESSAGE;
	}
	public InboundMessageBean(GatewayInfo gatewayInfo, String sender, String serviceCenter, String content, Date receiveDate) {
		this.gatewayInfo = gatewayInfo;
		this.sender = sender;
		this.serviceCenter = serviceCenter;
		this.content = content;
		this.receiveDate = receiveDate;
		this.status = InboundMessage.NEW_MESSAGE;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public GatewayInfo getGatewayInfo() {
		return gatewayInfo;
	}
	public void setGatewayInfo(GatewayInfo gatewayInfo) {
		this.gatewayInfo = gatewayInfo;
	}
	
	@Override
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getServiceCenter() {
		return serviceCenter;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getReceiveDate() {
		return receiveDate;
	}
	
	@Override
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Timestamp getVersion() {
		return version;
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
