package com.swg.sms.action;

import java.util.Set;

import com.swg.sms.entity.InboundMessage;

/**
 * Ini action, action dalam konteks sms command.
 * 
 * @author zakyalvan
 */
public interface ParameterizableAction {
	public static int KEY_NOT_VALID = 1;
	
	/**
	 * Key atau identifier dari action, satu kata yang mewakili masing-masing action.
	 * Key ini juga yang menjadi keyword dalam pesan masuk yang diterima,
	 * jadi pesan masuk dengan keyword yang tidak dikenali oleh system berarti pesan
	 * yang tidak valid.
	 * 
	 * @return
	 */
	String getKey();
	
	/**
	 * Set regular expression pattern dari key.
	 * Pola ini digunakan untuk validasi pattern.
	 * 
	 * @param String pattern
	 */
	void setKeyPattern(String pattern);
	
	/**
	 * Retrieve parameter yang diperlukan 
	 * 
	 * @return Set<String>
	 */
	Set<String> getRequiredParameterNames();
	
	/**
	 * Set required parameters name.
	 * 
	 * @param requiredParameterNames
	 */
	void setRequiredParameterNames(Set<String> requiredParameterNames);
	
	/**
	 * Get parameter setelah di parsing (execute).
	 * 
	 * @return {@link Set<Parameter<?>>}
	 */
	Set<Parameter<?>> getParameters();
	
	/**
	 * Set pola regular expression dari raw parameters.
	 * Pattern ini digunakan untuk validasi.
	 * 
	 * @param pattern
	 */
	void setRawParametersPattern(String pattern);
	
	/**
	 * Get raw parameter (string) dari sms yang diterima.
	 * 
	 * @return String
	 */
	String getRawParameters();
	
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
	 * Ekseskusi action.
	 * 
	 * @param message
	 * @throws ActionException
	 */
	void execute(InboundMessage message) throws ActionException;
}
