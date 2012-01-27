package com.swg.client.place;

import com.google.gwt.place.shared.Place;

/**
 * Kelas yang merepresentasiin 'tempat' dimana sms service setting dilakukan.
 * Kelas ini tanpa tokenizer, atau dengan kata lain tidak akan dihandle
 * dalam history management (Karena view akan ditampilin dalam window).
 * 
 * @author zakyalvan
 */
public class SmsServiceSettingPlace extends Place {
	private String name;
	public SmsServiceSettingPlace(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SmsServiceSettingPlace other = (SmsServiceSettingPlace) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
