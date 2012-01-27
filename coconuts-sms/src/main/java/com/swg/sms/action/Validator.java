package com.swg.sms.action;

public interface Validator<T> {
	void validate(T object);
}
