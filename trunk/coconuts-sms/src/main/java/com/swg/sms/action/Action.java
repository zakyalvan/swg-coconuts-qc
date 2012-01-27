package com.swg.sms.action;

import com.swg.sms.entity.InboundMessage;

/**
 * Kontrak dasar untu aksi yang bisa didispatch menggunakan pesan sms
 * (dengan format tertentu). Setiap action memiliki keyword yang sehingga
 * pesan dengan format.
 * 
 * @author zakyalvan
 */
public interface Action extends ValidationAware {
	Keyword getKeyword();
	
	boolean canExecute(InboundMessage message);
	void execute(InboundMessage message) throws ActionException;
}
