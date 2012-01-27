package com.swg.sms.action;

import java.io.Serializable;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

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
	
	public static final Pattern VALID_PATTERN = Pattern.compile("");
	public static final String DELIMITER = " ";
	
	private Logger logger = Logger.getLogger(getClass());
	
	private Integer partsCount = 0;
	private Integer parametersCount = 0;
	
	private String format;
	
	private FormatModel() {}
	
	public static final FormatModel decodeFromString(String format) {
		if(format == null || format.length() == 0)
			throw new IllegalArgumentException("Format parameter should not be null or zero length string.");
		
		Matcher validPatternMatcher = VALID_PATTERN.matcher(format);
		
		FormatModel formatModel = new FormatModel();
		while(validPatternMatcher.find()) {
			
		}
			
		return formatModel;
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
	
	/**
	 * Retrieve parameter name yang terdefinisi dalam format.
	 * 
	 * @return
	 */
	public Set<String> getParametersName() {
		return null;
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
