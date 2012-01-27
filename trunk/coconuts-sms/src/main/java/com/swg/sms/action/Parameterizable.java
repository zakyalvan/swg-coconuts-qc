package com.swg.sms.action;

import java.util.Set;

/**
 * Bagian dari action untuk berurusan dengan parameter pesan.
 * 
 * @author zakyalvan
 */
public interface Parameterizable {
	Set<String> getParametersName();
	Class<Parameter<?>> getParameterType(String name);
	
	void setParameter(Parameter<?> parameter);
}
