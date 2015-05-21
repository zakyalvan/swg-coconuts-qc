package com.swg.sms.processor;

import com.swg.sms.entity.InboundMessage;
import com.swg.sms.processor.MessageProcessingCallback.HasMessageProcessingCallback;

public interface MessageProcessor extends HasMessageProcessingCallback {
    void processMessage(InboundMessage inboundMessage) throws MessageProcessingException;
}
