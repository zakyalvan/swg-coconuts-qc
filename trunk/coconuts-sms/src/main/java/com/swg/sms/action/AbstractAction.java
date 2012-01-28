package com.swg.sms.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.swg.sms.action.param.Parameter;

/**
 * Ini base class untuk action.
 * Sebagai catatan untuk base kelas ini, setiap perubahan pada format, harus 
 * dicheck kesesesuaiannya dengan parameter yang diperlukan.
 * 
 * @author zakyalvan
 */
public abstract class AbstractAction implements Action {
	protected Logger logger = Logger.getLogger(getClass());
	
	protected boolean validationEnabled;
	
	protected final Keyword keyword;
	protected final Format format;
	
	protected final Set<Parameter<?>> parameters = new HashSet<Parameter<?>>();
	
	/**
	 * Map antara nama parameter yang diperlukan dengan jenis dari parameter tersebut.
	 */
	private Map<String, Class<? extends Parameter<?>>> parametersTypeMap = 
		new HashMap<String, Class<? extends Parameter<?>>>();
	
	protected boolean responseEnabled = true;

	public AbstractAction(Keyword keyword, Format format) {
		if(keyword == null || format == null)
			throw new IllegalArgumentException("Parameter format dan/atau keyword tidak boleh null.");
		
		logger.debug("Call method to configure parameters type mapping.");
		configureParameterTypeMap(parametersTypeMap);
		
		logger.debug("Call method to validate format supplied.");
		validateFormat(format);
		
		this.keyword = keyword;
		this.format = format;
	}
	
	/**
	 * Configure parameter type mapping, mapping antara nama parameter dan typenya.
	 * 
	 * @param parametersTypeMap2
	 */
	protected abstract void configureParameterTypeMap(Map<String, Class<? extends Parameter<?>>> parametersTypeMap2);
	
	/**
	 * Validasi format pesan yang dibuat, apakah sesuai dengan parameter yang diperlukan
	 * oleh action ini atau tidak.
	 * 
	 * @param {@link Format} format
	 */
	protected final void validateFormat(Format format) {
		Set<String> parametersNameInDefinedFormat = 
				new HashSet<String>(format.getModel().getParametersName());
		
		if(parametersNameInDefinedFormat.size() != parametersTypeMap.size()) {
			throw new IllegalArgumentException("Number of defined parameter in format and " +
					"required parameter not match.");
		}
		
		Set<String> parametersName = new HashSet<String>(parametersTypeMap.keySet());
		for(String nameInFormat : parametersNameInDefinedFormat) {
			// Terpaksa dilooping, ga bisa check equals-ignore-case isinya.
			for(String parameterName : parametersName) {
				if(parameterName.equalsIgnoreCase(nameInFormat)) {
					parametersName.remove(parameterName);
					
					Class<? extends Parameter<?>> typeInFormat = format.getModel().getParameterTypeFor(parameterName);
					Class<? extends Parameter<?>> typeRequired = parametersTypeMap.get(parameterName);
					if(!typeInFormat.isAssignableFrom(typeRequired)) {
						String errorMessage = "Parameter type (for parameter name : "+parameterName+") " +
								"defined in format ("+typeInFormat+") not match with required parameter type ("+typeRequired+")";
						logger.error(errorMessage);
						throw new IllegalArgumentException(errorMessage);
					}
				}
			}
		}
		
		/**
		 *  Check apakah masih ada sisa setelah di bandingkan dan diremove.
		 *  Jika masih ada berarti ada yang tidak match antara parameter yang didefinisiin
		 *  dalam format dengan parameter yang diperlukan dalam action ini.
		 */
		if(parametersName.size() > 0) {
			String errorMessage = "Parameter-name(s) defined in format ("+parametersNameInDefinedFormat+") " +
					"not match with parameter required by this action (" +parametersName+ ").";
			logger.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	public void setValidationEnabled(boolean validationEnabled) {
		this.validationEnabled = validationEnabled;
	}
	public boolean isValidationEnabled() {
		return validationEnabled;
	}

	public void addValidator(Validator<?> validator) {
		
	}
	public void removeValidator(Validator<?> validator) {
		
	}

	public void validate() throws ActionValidationException {
		
	}

	/**
	 * Retrieve copy of parameters name yang diperlukan untuk mengeksekusi
	 * action ini. Di kasi kopian biar aman saja.
	 * 
	 * @return Set<String>
	 */
	public Set<String> getParametersName() {
		return new HashSet<String>(parametersTypeMap.keySet());
	}

	public Class<? extends Parameter<?>> getParameterType(String name) {
		if(parametersTypeMap.containsKey(name)) {
			return parametersTypeMap.get(name);
		}
		return null;
	}

	protected Parameter<?> getParameter(String name) {
		Parameter<?> parameter = null;
		// Di loop biar bisa ignore case.
		for(Parameter<?> p : parameters) {
			if(p.getName().equalsIgnoreCase(name)) {
				parameter = p;
			}
		}
		
		if(parameter == null)
			throw new IllegalArgumentException("Parameter dengan name " + name + " tidak ditemukan dalam " + parameters);
		
		return parameter;
	}
	
	public void addParameter(Parameter<?> parameter) {
		this.parameters.add(parameter);
	}
	@Override
	public void setParameters(Collection<Parameter<?>> parameters) {
		this.parameters.addAll(parameters);
	}

	public Keyword getKeyword() {
		return keyword;
	}
	public Format getFormatReceived() {
		return format;
	}

	public void setResponseEnabled(boolean responseEnabled) {
		this.responseEnabled = responseEnabled;
	}
	public boolean isResponseEnabled() {
		return responseEnabled;
	}

	public void setResponseEnabledFor(int code) {
		
	}

	public boolean isResponseEnabledFor(int code) {
		return false;
	}

	public abstract void execute() throws ActionException;
}