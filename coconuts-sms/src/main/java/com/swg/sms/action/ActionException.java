package com.swg.sms.action;

/**
 * Exception yang dilempar pada saat eksekusi action gagal.
 * 
 * @author zakyalvan
 */
public class ActionException extends RuntimeException {
	private static final long serialVersionUID = -6963324075113801446L;
	
	protected int code;
	protected ParameterizableAction action;
	
	public ActionException(String message, int code, ParameterizableAction action) {
		super(message);
		assert(action != null) : "Parameter action tidak boleh null.";
		
		this.code = code;
		this.action = action;
	}

	public int getCode() {
		return code;
	}
	public ParameterizableAction getAction() {
		return action;
	}
}
