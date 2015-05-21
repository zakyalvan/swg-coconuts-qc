package com.swg.sms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Audit trail status dan aktivitas dari gateway.
 *
 * @author zakyalvan
 */
@Entity
@Table(name = "gateway_audit")
public class GatewayAudit implements Serializable {
    private static final long serialVersionUID = 1807583001377246472L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gateway_id")
    private GatewayInfo gateway;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private EventType eventType;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "audit_timestamp")
    private Date timestamp;

    GatewayAudit() {
    }

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

    public enum EventType {
        STARTED, STOPED, SEND_SMS, RECEIVE_SMS
    }
}
