package com.swg.sms.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "outbound_message")
public class OutboundMessageBean implements OutboundMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "gateway_info")
    private GatewayInfo gatewayInfo;

    @NotBlank
    @Column(name = "recipient", length = 14)
    private String recipient;

    @NotBlank
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "status", length = 1)
    private Integer status = OutboundMessage.NEW_MESSAGE;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_date")
    private Date sentDate;

    @Version
    @Column(name = "version")
    private Timestamp version;

    public OutboundMessageBean() {
    }

    public OutboundMessageBean(String recipient, String content) {
        this.recipient = recipient;
        this.content = content;
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

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
