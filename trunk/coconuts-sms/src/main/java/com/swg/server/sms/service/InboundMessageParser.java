package com.swg.server.sms.service;

import com.swg.server.sms.entity.InboundMessage;

/**
 * Kontrak strategi parser untuk inbound message.
 * Setiap command (reg, suara dll) harus memiliki satu object parser tersendiri.
 * 
 * @author zakyalvan
 */
public interface InboundMessageParser {
	boolean canParse(InboundMessage message);
	ParsedInboundMessage parse(InboundMessage message) throws CantParseMessageException;
}