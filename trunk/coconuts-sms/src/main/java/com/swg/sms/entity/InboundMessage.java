package com.swg.sms.entity;

import java.util.Date;

/**
 * Kontrak untuk pesan masuk.
 * 
 * @author zakyalvan
 */
public interface InboundMessage {
	/**
	 * Flag status yang menandakan message baru, belum/harus diproses 
	 * oleh system.
	 */
	public static final Integer NEW_MESSAGE = 1;
	
	/**
	 * Flag status yang menunjukan inbound message bersangkutan sedang diproses.
	 */
	public static final Integer PROCESSING_MESSAGE = 2;
	
	/**
	 * Flag status yang menunjukan pesan sudah berhasil diproses, dan
	 * action yang terkait sudah berhasil didispatch.
	 */
	public static final Integer PROCESSED_MESSAGE = 3;
	
	/**
	 * Flag status yang nunjukin pesan gagal diproses, dengan alasan apapun.
	 */
	public static final Integer FAILED_MESSAGE = 4;
	
	/**
	 * Flag status yang harus diset oleh sytem pada saat 
	 * message yang bersangkutan harus diproses ulang.
	 * Contohnya use case adalah pada saat perbaikan format pesan.
	 */
	public static final Integer REPROCESS_MESSAGE = 5;
	
	Integer getId();
	GatewayInfo getGatewayInfo();
	String getSender();
	String getServiceCenter();
	String getContent();
	Date getReceiveDate();
	Integer getStatus();
	void setStatus(Integer status);
	Date getVersion();
}