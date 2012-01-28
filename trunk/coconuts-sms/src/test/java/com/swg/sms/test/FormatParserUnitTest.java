package com.swg.sms.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.swg.sms.action.Format;
import com.swg.sms.action.Parser;
import com.swg.sms.action.Parser.Result;
import com.swg.sms.action.SimpleFormat;
import com.swg.sms.action.SimpleParser;

public class FormatParserUnitTest {
	private Logger logger = Logger.getLogger(getClass());
	
	@Test
	public void testParser() {
		logger.debug("Test parser.");
		Format format = new SimpleFormat("{keyword}{parameter:string:content}");
		Parser parser = new SimpleParser();
		Result parsingResult = parser.parse("input iniadalahcontent", format);
		logger.debug("Parsing result : " + parsingResult);
	}
}
