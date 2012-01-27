package com.swg.sms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="om")
public class OutboundMessageBean implements OutboundMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String receiver;
	private String content;
	private int status = 1;
	
	public OutboundMessageBean() {}
	public OutboundMessageBean(String receiver, String content) {
		super();
		this.receiver = receiver;
		this.content = content;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public String getReceiver() {
		return receiver;
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
}
