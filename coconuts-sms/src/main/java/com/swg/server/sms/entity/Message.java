package com.swg.server.sms.entity;

import java.io.Serializable;

public interface Message extends Serializable {
	Integer getId();
	String getContent();
}
