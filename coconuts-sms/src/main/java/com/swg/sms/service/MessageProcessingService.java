package com.swg.sms.service;

import com.swg.sms.entity.InboundMessage;
import com.swg.sms.entity.OutboundMessage;

public interface MessageProcessingService {
    void reprocessMessage(InboundMessage inboundMessage);

    void processMessage(InboundMessage inboundMessage);

    void processMessage(OutboundMessage outboundMessage, boolean enforceAlreadyProcessedMessage);

    void processMessage(OutboundMessage outboundMessage);
}
