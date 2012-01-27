package com.swg.sms.action;

import java.util.Map;

public class MapParameter<V> implements Parameter<Map<String, V>> {
	@Override
	public String getName() {
		return null;
	}

	@Override
	public Class<Map<String, V>> getType() {
		return null;
	}

	@Override
	public Map<String, V> getValue() {
		return null;
	}
	
}
