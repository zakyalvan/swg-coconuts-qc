package com.swg.sms.action;

public interface Parameter<T> {
	String getName();
	Class<T> getType();
	T getValue();
}