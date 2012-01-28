package com.swg.sms.action;

import java.io.Serializable;
import java.util.Set;

/**
 * Simple kelas dari object yang nampung format pesan masuk yang bisa dihandle
 * untuk mengexecute action tertentu.
 * 
 * @author zakyalvan
 */
public abstract class Format implements Serializable {
	public interface FormatModel {
		public String getFormatString();
		
		/**
		 * Retrieve position dari keyword.
		 * Index ini ditentukan setelah format di split menjadi beberapa bagian
		 * (token format) dan displit berdasarkan delimiter.
		 * 
		 * @return Integer
		 */
		public Integer getKeywordPosition();
		
		/**
		 * Count seluruh parameter yang didefinisikan dalam format.
		 * 
		 * @return
		 */
		public Integer countParameters();
		/**
		 * Retrieve parameter name yang terdefinisi dalam format.
		 * 
		 * @return
		 */
		public Set<String> getParametersName();
		public String getParameterNameAt(Integer position);
		public Integer getParameterPosition(String name);
		public Class<? extends Parameter<?>> getParameterTypeAt(Integer position);
		public Class<? extends Parameter<?>> getParameterTypeFor(String name);
	}
	
	private static final long serialVersionUID = -1107375968806033065L;
	
	protected final String value;
	protected final FormatModel model;
	
	public Format(String value) {
		if(value == null || value.trim().length() ==0)
			throw new IllegalArgumentException("Parameter format value should not be null or zero length string.");
		
		this.value = value;
		this.model = createModel(value);
	}
	
	protected abstract FormatModel createModel(String value);

	public String getValue() {
		return value;
	}
	public FormatModel getModel() {
		return model;
	}
	
	public String toString() {
		return value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Format other = (Format) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equalsIgnoreCase(other.value))
			return false;
		return true;
	}
}
