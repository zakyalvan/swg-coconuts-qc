package com.swg.sms.action;

public class StringParameter implements Parameter<String> {
	private String name;
	private String value;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	public String getValue() {
		return value;
	}
}
