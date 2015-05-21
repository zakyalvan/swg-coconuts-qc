package com.swg.sms.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "inbound_message")
public class InboundMessageBean implements InboundMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "gateway_info_id")
    private GatewayInfo gatewayInfo;

    @NotBlank
    @Column(name = "sender", length = 14)
    private String sender;

    @Column(name = "service_center", length = 14)
    private String serviceCenter;

    @NotBlank
    @Column(name = "content")
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "receive_date")
    private Date receiveDate;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @Version
    @Column(name = "version")
    private Timestamp version;

    public InboundMessageBean() {
        this.status = InboundMessage.NEW_MESSAGE;
    }

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

    @Override
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

    @Override
    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = new Timestamp(version.getTime());
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
