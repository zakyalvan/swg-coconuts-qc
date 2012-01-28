package com.swg.sms.action;

/**
 * Implementasi default dan sederhan {@link Format}
 * 
 * @author zakyalvan
 */
public class SimpleFormat extends Format {
	private static final long serialVersionUID = -8704357620618037624L;

	public SimpleFormat(String value) {
		super(value);
	}
	@Override
	protected FormatModel createModel(String value) {
		return SimpleFormatModel.decodeFromString(value);
	}
}
