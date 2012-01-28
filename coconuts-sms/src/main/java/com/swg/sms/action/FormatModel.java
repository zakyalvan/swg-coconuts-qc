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
	
	private static Logger logger = Logger.getLogger(FormatModel.class);
	
	public static final Pattern VALID_FORMAT_PATTERN = 
			Pattern.compile("(\\G\\s*?\\{\\s*?(([keyword]{6,8}?)|([parmet]{8,10}\\s*?:\\s*?(string|number|map){1}\\s*?:\\s*?(\\w*?){1}))\\s*?\\}\\s*?)\\1*?");
	
	public static final String DEFAULT_START_TAG = "{";
	public static final String DEFAULT_END_TAG = "}";
	
	public static final FormatModel decodeFromString(String format) {
		if(format == null || format.length() == 0)
			throw new IllegalArgumentException("Format parameter should not be null or zero length string.");
		
		// Trim dahulu fomatnya.
		format = format.trim();
		int formatLength = format.length();
		
		Matcher validPatternMatcher = VALID_FORMAT_PATTERN.matcher(format);
		
		FormatModel formatModel = new FormatModel(format);
		
		int lastEnd = 0;
		boolean keywordPartFound = false;
		while(validPatternMatcher.find()) {
			lastEnd = validPatternMatcher.end();
			
			if(validPatternMatcher.group(3) != null) {
				logger.info("Keyword part found.");
			}
		}
		
		if(lastEnd != formatLength)
			throw new IllegalArgumentException("Terjadi kesalahan pada format yang didefinisikan " +
					"(Dimulai sekitar \""+format.substring(lastEnd)+"\")");
		
		return formatModel;
	}
	
	private Integer partsCount = 0;
	private Integer parametersCount = 0;
	
	private String format;
	
	private FormatModel(String format) {
		this.format = format;
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
