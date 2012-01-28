package com.swg.sms.action.param;

public interface Parameter<T> {
	String getName();
	Class<T> getType();
	T getValue();
}