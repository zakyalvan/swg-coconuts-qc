package com.swg.sms.action;

import java.io.Serializable;

public final class Format implements Serializable {
	private static final long serialVersionUID = -1107375968806033065L;
	
	private String value;
	private FormatModel model;
	
	public Format(String value) {
		this.value = value;
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
