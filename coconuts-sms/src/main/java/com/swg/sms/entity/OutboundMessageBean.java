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
@Table(name="om")
public class OutboundMessageBean implements OutboundMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="gateway_info")
	private GatewayInfo gatewayInfo;
	
	@NotBlank
	@Column(name="recipient", length=14)
	private String recipient;
	
	@NotBlank
	@Column(name="content")
	private String content;
	
	@NotNull
	@Column(name="status", length=1)
	private Integer status;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sent_date")
	private Date sentDate;
	
	@Version
	@Column(name="version")
	private Timestamp version;
	
	public OutboundMessageBean() {}
	public OutboundMessageBean(String recipient, String content) {
		this.recipient = recipient;
		this.content = content;
		this.status = OutboundMessage.NEW_MESSAGE;
		this.createDate = new Date();
		
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public GatewayInfo getGatewayInfo() {
		return gatewayInfo;
	}
	public void setGatewayInfo(GatewayInfo gatewayInfo) {
		this.gatewayInfo = gatewayInfo;
	}
	
	@Override
	public String getRecipient() {
		return recipient;
	}
	
	@Override
	public String getContent() {
		return content;
	}
	@Override
	public int getStatus() {
		return status;
	}
	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public Date getCreateDate() {
		return createDate;
	}
	
	@Override
	public Date getSentDate() {
		return sentDate;
	}
	@Override
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	@Override
	public Date getVersion() {
		return version;
	}
	
	@Override
	public String toString() {
		return "OutboundMessageBean [id=" + id + ", gatewayInfo=" + gatewayInfo
				+ ", recipient=" + recipient + ", content=" + content
				+ ", status=" + status + ", createDate=" + createDate
				+ ", sentDate=" + sentDate + ", version=" + version + "]";
	}
}
