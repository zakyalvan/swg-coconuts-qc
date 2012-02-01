package com.swg.sms.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.swg.sms.format.Format;
import com.swg.sms.format.Parser;
import com.swg.sms.format.SimpleFormat;
import com.swg.sms.format.SimpleParser;
import com.swg.sms.format.Parser.Result;

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
