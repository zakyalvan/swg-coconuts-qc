package com.swg.sms.action;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.swg.sms.action.param.Parameter;
import com.swg.sms.action.param.StringParameter;

/**
 * Ini contoh action saja.
 * Untuk kebutuhan test aja.
 * 
 * @author zakyalvan
 */
@Component
public class ExampleEchoAction extends AbstractAction {
	static final Keyword keywordIni = new SimpleKeyword("echo");
	static final Format formatIni = new SimpleFormat("{keyword}{parameter:string:echoContent}");
	
	public ExampleEchoAction() {	
		super(keywordIni, formatIni);
	}

	@Override
	public void execute() throws ActionException {
		logger.info("========================================= Start : Exceute echo action.");
		logger.info("Yang dikirim dari user : " + getParameter("echoContent"));
		logger.info("========================================= End : Exceute echo action.");
		
		try {
			
			Runtime.getRuntime().exec("notepad");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void configureParameterTypeMap(Map<String, Class<? extends Parameter<?>>> parametersTypeMap) {
		parametersTypeMap.put("echoContent", StringParameter.class);
	}
}
