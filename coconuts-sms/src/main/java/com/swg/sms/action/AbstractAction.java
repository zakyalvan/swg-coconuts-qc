package com.swg.sms.action;

import java.util.HashSet;
import java.util.Set;

/**
 * Ini base class untuk action.
 * 
 * @author zakyalvan
 */
public abstract class AbstractAction implements Action {
	protected String key;
	protected String keyPattern;
	protected Set<String> requiredParameterNames = new HashSet<String>();
	
	protected Set<Parameter<?>> parameters = new HashSet<Parameter<?>>();
	
	protected String rawParameterPattern;
	protected String rawParameters;
	
	protected boolean responseEnabled;
}
