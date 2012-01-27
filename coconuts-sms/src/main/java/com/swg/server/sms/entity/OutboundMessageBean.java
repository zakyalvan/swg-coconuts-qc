package com.swg.server.sms.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="outbound_message")
public class OutboundMessageBean implements OutboundMessage {
	private static final long serialVersionUID = 7244159795217586813L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column
	private String content;
	
	@NotBlank
	@Column
	private String recipient;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="gateway_id")
	private GatewayInfo gatewayInfo;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentDate;
	
	@Version
	private Timestamp version;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public String getRecipient() {
		return recipient;
	}

	@Override
	public GatewayInfo getGatewayInfo() {
		return gatewayInfo;
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
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public Date getSentDate() {
		return sentDate;
	}
	@Override
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	public Date getVersion() {
		return version;
	}
}
