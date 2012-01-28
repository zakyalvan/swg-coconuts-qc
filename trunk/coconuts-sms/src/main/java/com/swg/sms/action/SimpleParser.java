package com.swg.sms.action;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.swg.sms.action.param.MapParameter;
import com.swg.sms.action.param.NumberParameter;
import com.swg.sms.action.param.Parameter;
import com.swg.sms.action.param.StringParameter;

/**
 * Implementasi sederhana {@link Parser}. Kelas object yang digunakan untuk
 * memparsing isi pesan masuk sebelum action dieksekusi. Proses parsing ini dihint oleh
 * object {@link Format}, yaitu format valid yang dapat diterima oleh action.
 * 
 * @author zakyalvan
 */
@Component("simpleParser")
public class SimpleParser implements Parser {
	private Logger logger = Logger.getLogger(getClass());
	
	private String delimiter = DEFAULT_DELIMITER;
	
	public SimpleParser() {}
	public SimpleParser(String delimiter) {
		this.delimiter = delimiter;
	}
	
	/**
	 * TODO Sempurnakan method ini biar bisa nge-handle advance use case.
	 * 		Misalnya string normalization (auto-correct) atau nilai tambah lainnya.
	 */
	@Override
	public Result parse(String payload, Format format) throws ParsingException {
		logger.info("Parsing message payload.");
		String[] payloadParts = payload.split(delimiter);
		if(payloadParts.length != format.getModel().countParameters()+1) {
			/**
			 * TODO Extends functionality di sini.
			 */
			logger.error("Pesan tidak valid. Jumlah bagian dalam isi pesan tidak sama dengan jumlah bagian yang dibutuhkan dalam format");
			throw new ParsingException("Pesan tidak valid. Jumlah bagian dalam isi pesan tidak sama dengan jumlah bagian yang dibutuhkan dalam format.", this);
		}
		
		String keyword = null;
		Set<Parameter<?>> parameters = new HashSet<Parameter<?>>();
		for(int i = 1; i <= payloadParts.length; i++) {
			if(format.getModel().getKeywordPosition() == i) {
				keyword = payloadParts[i];
			}
			else {
				String parameterName = format.getModel().getParameterNameAt(i);
				String parameterValue = payloadParts[i];
				Class<? extends Parameter<?>> parameterType = format.getModel().getParameterTypeAt(i);
				
				if(StringParameter.class.isAssignableFrom(parameterType)) {
					StringParameter stringParameter = new StringParameter(parameterName, parameterValue);
					parameters.add(stringParameter);
				}
				else if(NumberParameter.class.isAssignableFrom(parameterType)) {
					try {
						Integer value = Integer.parseInt(parameterValue.trim());
						parameters.add(new NumberParameter(parameterName, value));
					}
					catch (NumberFormatException e){
						/**
						 * TODO Tambahin fitur koreksi disini untuk perbaikan :
						 * 		- Check apakah ada angka 0 yang keganti dengan huruf O, ganti dengan angka.
						 * 		- Ada character antara digit, jika ya hapus character tersebut.
						 * 		- dst.
						 */
						logger.error("Format angka dalam parameter pesan tidak valid.");
						throw new ParsingException("Format angka dalam parameter pesan tidak valid.", this);
					}
				}
				else if(MapParameter.class.isAssignableFrom(parameterType)) {
					
				}
				else {
					throw new ParsingException("Parameter type ("+parameterType+") tidak dikenali.", this);
				}
			}
		}
		return new Result(keyword, parameters);
	}
}
