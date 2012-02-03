package com.swg.initiator.convert;

public interface EntityConversionUtil {
	
	static final String PHONE_PATTERN="^((\\+62)|0)\\d{7,11}+";
	
	static final String NAME_PATTERN="[A-Z][a-z]+( [A-Z][a-z]+)?";
	
	static final String NAMA_WILAYAH_PATTERN="(\\p{Upper}(\\p{Alpha}+\\s?)){2,3}";
	
	static final String KODE_WILAYAH_PATTERN="^\\d{1,2}";
	
	static final String KODE_KELURAHAN_PATTERN="^[1-9]\\d{1,4}";
	

}
