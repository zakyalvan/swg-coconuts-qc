package com.swg.core.event;

import org.springframework.context.ApplicationEvent;

/**
 * Event yang dipublish (untuk diquery oleh client) jika ada perubahan state pada
 * aplikasi (Entah itu event purubahan data hasil perhitungan dan lain-lain.).
 * 
 * @author zakyalvan
 */
public class ApplicationNotificationEvent extends ApplicationEvent {
	private static final long serialVersionUID = 2948600362533974135L;

	private Integer eventCode;
	
	public ApplicationNotificationEvent(Object source) {
		super(source);
	}

	public Integer getEventCode() {
		return eventCode;
	}
}
