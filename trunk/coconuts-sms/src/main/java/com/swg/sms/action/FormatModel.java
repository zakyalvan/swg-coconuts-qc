package com.swg.sms.action;

import java.io.Serializable;

/**
 * Ini representasi dari format sms yang dimengerti oleh system.
 * Setiap action memiliki satu object format ini (Atau dibikin 
 * bisa lebih dari satu juga? Sebagai alternative. Untuk sementara satu
 * aja dulu kali ya). Format ini menunjukan bagaimana sususan {@link Keyword}
 * dan {@link Parameter} dalam satu action. Sebagai contoh, isi dari object ini adalah
 * 
 * {keyword}<space>{parameter:string:parameterName1}<space>{parameter:map:parameterName2}<space>{parameter:number:parameterName3}
 * 
 * 
 * @author zakyalvan
 */
public final class FormatModel implements Serializable {
	private static final long serialVersionUID = -5492323427440482670L;
	
	public static final String DELIMITER = " ";
	
	private Integer partsCount = 0;
	private Integer parametersCount = 0;
	
	private String format;
	
	public FormatModel(String format) {
		this.format = format;
		
		/**
		 * Parsing format di sini.
		 */
	}
	
	/**
	 * Retrieve index dari keyword.
	 * Index ini ditentukan setelah format di split menjadi beberapa bagian
	 * (token format) dan displit berdasarkan delimiter.
	 * 
	 * @return Integer
	 */
	public Integer getKeywordIndex() {
		return null;
	}
	
	/**
	 * Count seluruh bagian yang ada dalam format.
	 * 
	 * @return
	 */
	public Integer countParts() {
		return partsCount;
	}
	
	/**
	 * Count seluruh parameter yang didefinisikan dalam format.
	 * 
	 * @return
	 */
	public Integer countParameters() {
		return parametersCount;
	}
	
	public String getParameterNameAt(Integer index) {
		return null;
	}
	
	public Integer getParameterIndexFor(String name) {
		return null;
	}
	
	public Class<Parameter<?>> getParameterTypeAt(Integer index) {
		return null;
	}
	public Class<Parameter<?>> getParameterTypeFor(String name) {
		return null;
	}
}
