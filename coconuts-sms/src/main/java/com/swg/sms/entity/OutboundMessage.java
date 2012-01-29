package com.swg.sms.entity;

import java.util.Date;

/**
 * Kontrak untuk pesan keluar.
 * 
 * @author zakyalvan
 */
public interface OutboundMessage {
	/**
	 * Status untuk pesan keluar baru. Pesan keluar dengan status {@link #NEW_MESSAGE}
	 * berarti pesan tersebut belum diproses atau dikirim.
	 */
	public static final int NEW_MESSAGE = 1;
	
	/**
	 * Pesan keluar dengan status {@link #SEND_MESSAGE} berarti pesan tersebut dalam
	 * proses pengiriman.
	 */
	public static final int SEND_MESSAGE = 2;
	
	/**
	 * Pesan keluar dengan status {@link #SENT_MESSAGE} berarti pesan tersebut sudah
	 * berhasil dikirim.
	 */
	public static final int SENT_MESSAGE = 3;
	
	/**
	 * Pesan keluar dengan status {@link #UNSENT_MESSAGE} berarti pesan tersebut tidak
	 * terkirim.
	 */
	public static final int UNSENT_MESSAGE = 4;
	
	/**
	 * Status pesan keluar yang gagal dikirim.
	 */
	public static final int FAILED_MESSAGE = 5;
	
	Integer getId();
	GatewayInfo getGatewayInfo();
	String getRecipient();
	String getContent();
	int getStatus();
	void setStatus(int status);
	Date getCreateDate();
	Date getSentDate();
	void setSentDate(Date sentDate);
	
	Date getVersion();
}
