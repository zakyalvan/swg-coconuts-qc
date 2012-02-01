package com.swg.sms.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.swg.sms.format.SimpleFormatModel;

public class FormatModelUnitTest {
	private Logger logger = Logger.getLogger(getClass());
	@Test
	public void createModelTest() {
		logger.debug("===============================createModelTest");
		SimpleFormatModel.decodeFromString("{keyword} {prameter:string:name}");
		SimpleFormatModel.decodeFromString("{keyword}");
	}
	@Test(expected=IllegalArgumentException.class)
	public void createInvalidModelTest() {
		logger.debug("===============================createInvalidModelTest");
		SimpleFormatModel.decodeFromString("{keyword} {prameter:string:name");
	}
	@Test(expected=IllegalArgumentException.class)
	public void createInvalidMode2Test() {
		logger.debug("===============================createInvalidModel2Test");
		SimpleFormatModel.decodeFromString("{keyword} {prameter:string:name}{prameter:string:name}");
	}
	@Test(expected=IllegalArgumentException.class)
	public void createInvalidMode21Test() {
		logger.debug("===============================createInvalidModel21Test");
		SimpleFormatModel.decodeFromString("{keyword} {prameter:string:name}{prameter:string:NamE}");
	}
	@Test(expected=IllegalArgumentException.class)
	public void createInvalidMode3Test() {
		logger.debug("===============================createInvalidModel3Test");
		SimpleFormatModel.decodeFromString("{prameter:string:name}{keyword}{prameter:string:name}");
	}
	@Test(expected=IllegalArgumentException.class)
	public void createInvalidMode4Test() {
		logger.debug("===============================createInvalidModel4Test");
		SimpleFormatModel.decodeFromString("{keyword}{prameter:strng:name}");
	}
}
