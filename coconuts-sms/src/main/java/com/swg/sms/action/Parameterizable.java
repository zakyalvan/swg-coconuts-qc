package com.swg.sms.action;

import java.util.Collection;
import java.util.Set;

import com.swg.sms.action.param.Parameter;

/**
 * Bagian dari action untuk berurusan dengan parameter.
 * 
 * @author zakyalvan
 */
public interface Parameterizable {
	Set<String> getParametersName();
	Class<? extends Parameter<?>> getParameterType(String name);
	
	void addParameter(Parameter<?> parameter);
	void setParameters(Collection<Parameter<?>> parameters);
}
