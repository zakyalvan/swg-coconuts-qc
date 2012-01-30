package com.swg.web.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Place 'tempat' urusan manajemen service perpesanan.
 * 
 * @author zakyalvan
 */
public class SmsServicePlace extends Place {
	@Prefix("service")
	public static class Tokenizer implements PlaceTokenizer<SmsServicePlace> {
		@Override
		public SmsServicePlace getPlace(String token) {
			return new SmsServicePlace(token);
		}

		@Override
		public String getToken(SmsServicePlace place) {
			return place.getToken();
		}
	}
	
	private String token;
	
	public SmsServicePlace(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		SmsServicePlace other = (SmsServicePlace) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
}
