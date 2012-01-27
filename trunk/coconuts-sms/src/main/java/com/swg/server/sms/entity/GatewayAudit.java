package com.swg.server.sms.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 * Audit trail status dan aktivitas dari gateway.
 * 
 * @author zakyalvan
 */
@Entity
@Table(name="gateway_audit")
public class GatewayAudit implements Serializable {
	private static final long serialVersionUID = 1807583001377246472L;

	public enum EventType {
		STARTED, STOPED, SEND_SMS, RECEIVE_SMS
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="gateway_id")
	private GatewayInfo gateway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length=10)
	private EventType eventType;
	
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="audit_timestamp")
	private Date timestamp;
	
	GatewayAudit() {}
	public GatewayAudit(EventType eventType) {
		this.eventType = eventType;
	}
	public GatewayAudit(EventType eventType, String description) {
		this.eventType = eventType;
		this.description = description;
	}
	public GatewayAudit(EventType eventType, String description, Date timestamp) {
		this.eventType = eventType;
		this.description = description;
		this.timestamp = timestamp;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public GatewayInfo getGateway() {
		return gateway;
	}
	public void setGateway(GatewayInfo gateway) {
		this.gateway = gateway;
	}
	
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTimestamp() {
		return timestamp;
	}
}
