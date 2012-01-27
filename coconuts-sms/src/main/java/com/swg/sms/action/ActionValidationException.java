package com.swg.sms.action;

public class ActionValidationException extends ActionException {
	private static final long serialVersionUID = 4861114457052334488L;

	public ActionValidationException(String message, int code, ParameterizableAction action) {
		super(message, code, action);
	}
}
