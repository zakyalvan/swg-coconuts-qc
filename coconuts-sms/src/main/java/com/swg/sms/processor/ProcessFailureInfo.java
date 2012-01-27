package com.swg.sms.processor;

import java.io.Serializable;

import com.swg.sms.entity.InboundMessage;
import com.swg.sms.processor.MessageProcessingException.FailedStep;

/**
 * Informasi kegagalan pemrosesan pesan masuk.
 * 
 * @author zakyalvan
 */
public final class ProcessFailureInfo implements Serializable {
	private static final long serialVersionUID = 8449848118458958772L;
	
	private final InboundMessage processedMessage;
	private final int code;
	private final FailedStep failedStep;
	private final Throwable failureCause;
	
	public ProcessFailureInfo(InboundMessage processedMessage, int code, FailedStep failedStep, Throwable failureCause) {
		this.processedMessage = processedMessage;
		this.code = code;
		this.failedStep = failedStep;
		this.failureCause = failureCause;
	}
	
	public InboundMessage getProcessedMessage() {
		return processedMessage;
	}
	public int getCode() {
		return code;
	}
	public FailedStep getFailedStep() {
		return failedStep;
	}
	public Throwable getFailureCause() {
		return failureCause;
	}

	@Override
	public String toString() {
		return "ProcessFailureInfo [code=" + code + ", failedStep="
				+ failedStep + ", failureCause=" + failureCause 
				+"(with message : " + failureCause != null ? failureCause.getMessage() : "" + ")" + "]";
	}
}
