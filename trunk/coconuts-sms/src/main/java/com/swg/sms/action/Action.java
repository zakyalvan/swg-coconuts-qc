package com.swg.sms.action;

import com.swg.sms.entity.InboundMessage;

/**
 * Kontrak dasar untu aksi yang bisa didispatch menggunakan pesan sms
 * (dengan format tertentu). Setiap action memiliki keyword yang sehingga
 * pesan dengan format.
 * 
 * @author zakyalvan
 */
public interface Action extends ValidationAware, Parameterizable {
	Keyword getKeyword();
	
	/**
	 * Retrieve format content masuk yang bisa diterima atau dieksekusi oleh
	 * action ini. Masing-masing action memiliki satu format yang unik, tentu saja
	 * isi format ini tergantung dari parameter yang diperlukan untuk mengeksekusi
	 * underlying business service.
	 * 
	 * @return {@link Format}
	 */
	Format getFormatReceived();
	
	/**
	 * Set format yang diterima oleh action. Lihat {@link #getFormatReceived()}.
	 * Dalam method ini harus dicheck, apakah isi format (dari modelnya) apakah
	 * sesuai dengan parameter yang diperlukan atau tidak.
	 * 
	 * @param format
	 */
	void setFormatReceived(Format format);
	
	/**
	 * Enable/disable response untuk action ini secara keseluruhan.
	 * Jika didisable disini, response untuk jenis event tertentu
	 * diabaikan.
	 * 
	 * @param enabled
	 */
	void setResponseEnabled(boolean enabled);
	
	/**
	 * Apakah action ini dienable untuk memberikan response.
	 * Ini mengenable response secara keseluruhan dalam action ini 
	 * (untuk semua jenis event dalam konteks action ini).
	 * 
	 * @return boolean
	 */
	boolean isResponseEnabled();
	
	/**
	 * Enable response untuk jenis event tertentu.
	 * Jenis event dibedakan berdasarkan code yang menjadi parameter.
	 * 
	 * @param code
	 */
	void setResponseEnabledFor(int code);
	
	/**
	 * Apakah response dienable untuk event tertentu. Jenis event dibedakan
	 * berdasarkan kode yang diberikan.
	 * 
	 * @param code
	 * @return {@link Boolean}
	 */
	boolean isResponseEnabledFor(int code);
	
	/**
	 * Check apakah action ini dapat dieksekusi berdasarkan pesan masuk
	 * yang terima.
	 * 
	 * @param message
	 * @return
	 */
	boolean canExecute(InboundMessage message);
	void execute(InboundMessage message) throws ActionException;
}
