package com.swg.sms.processor;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swg.sms.action.Action;
import com.swg.sms.action.ActionStack;
import com.swg.sms.action.ActionStackFactory;
import com.swg.sms.action.Format;
import com.swg.sms.action.Parser;
import com.swg.sms.action.ParsingException;
import com.swg.sms.action.param.Parameter;
import com.swg.sms.entity.InboundMessage;

/**
 * Implementasi default dari {@link MessageProcessor}.
 * Object yang bertugas memproses pesan masuk yang diterima oleh system.
 * 
 * @author zakyalvan
 */
@Component
public class MessageProcessorImpl implements MessageProcessor {
	protected final Logger logger = Logger.getLogger(getClass());
	
	@Autowired(required=true)
	protected ActionStackFactory actionStackFactory;
	
	@Autowired(required=true)
	protected Parser parser;
	
	protected MessageProcessingCallback messageProcessorCallback = MessageProcessingCallback.DUMB;
	
	@Autowired(required=false)
	public void setProcessorCallback(MessageProcessingCallback messageProcessorCallback) {
		assert(messageProcessorCallback != null) : "Parameter processor callback tidak boleh null";
		this.messageProcessorCallback = messageProcessorCallback;
	}

	public void processMessage(InboundMessage inboundMessage) throws MessageProcessingException {
		try {
			logger.info("Eksekusi pre process callback.");
			messageProcessorCallback.onPreProcess(inboundMessage);
			
			doProcess(inboundMessage);
			
			logger.info("Eksekusi post processor callback.");
			messageProcessorCallback.onPostProcess(inboundMessage);
		}
		catch(MessageProcessingException e) {
			logger.error("Exception (" + e + ") dilempar pada saat pemrosesan pesan. Panggil failure callback.");
			messageProcessorCallback.onProcessFailure(
				new ProcessFailureInfo(inboundMessage, e.getCode(), e.getFailedStep(), e.getCause()));
			
			logger.info("Lempar kembali processing exception, biar invoker tau ada exception.");
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void doProcess(InboundMessage inboundMessage) {
		logger.info("Process pesan masuk.");
		
		ActionStack actionStack = actionStackFactory.create();
		boolean executed = false;
		while(!actionStack.empty()) {
			Action action = actionStack.pop();
			Format format = action.getFormatReceived();
			
			try {
				logger.info("Try to parse message.");
				Parser.Result result = parser.parse(inboundMessage.getContent(), format);
				
				action.setParameters((Collection<Parameter<?>>) result.getParameters());
				action.execute();
				executed = true;
			}
			catch(ParsingException parsingException) {
				// Disini message-processing-exception tidak dilempar, biar action berikutnya bisa dieksekusi.
				logger.error("Action ("+action+") tidak dapat memparsing content sms. Coba dengan action yang lain.", parsingException);
			}
		}
		if(!executed) {
			logger.error("Format pesan masuk invalid, tidak ada action yang sesuai untuk dieksekusi.");
			throw new MessageProcessingException("Format pesan masuk invalid, tidak ada action yang sesuai untuk dieksekusi.", 1);
		}
	}
}
