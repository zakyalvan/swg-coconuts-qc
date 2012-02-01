package com.swg.sms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Info atau parameter dari gateway.
 * 
 * @author zakyalvan
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="gateway_type")
@Table(name="gateway_info")
public abstract class GatewayInfo implements Serializable {
	protected static final long serialVersionUID = 6483948219953447247L;
	
	public static final String SERIAL_GATEWAY_TYPE = "SerialGatewayInfo";
	
	public static final Integer ALL_CAPABILITY = 1;
	public static final Integer INBOUND_CAPABILITY = 2;
	public static final Integer OUTBOUND_CAPABILITY = 3;
	
	@Id
	@Column(name="gateway_id")
	protected String id;
	
	@NotNull
	@Column(length=1)
	protected Integer capability;
	
	protected boolean enabled;
	protected boolean activated;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="gateway")
	protected Set<GatewayAudit> audits = new HashSet<GatewayAudit>();
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date version;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updatedAt;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="gatewayInfo", targetEntity=InboundMessageBean.class)
	protected Set<InboundMessage> inboundMessages = new HashSet<InboundMessage>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="gatewayInfo", targetEntity=OutboundMessageBean.class)
	protected Set<OutboundMessage> outboundMessages = new HashSet<OutboundMessage>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getCapability() {
		return capability;
	}
	public void setCapability(Integer capability) {
		this.capability = capability;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public Set<GatewayAudit> getAudits() {
		return audits;
	}
	public void setAudits(Set<GatewayAudit> audits) {
		this.audits = audits;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getVersion() {
		return version;
	}
}