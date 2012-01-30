package com.swg.web.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Place yang merepresentasi halaman utama aplikasi.
 * 
 * @author zakyalvan
 */
public class DashBoardPlace extends Place {
	@Prefix("homepage")
	public static class Tokenizer implements PlaceTokenizer<DashBoardPlace> {
		@Override
		public DashBoardPlace getPlace(String token) {
			return new DashBoardPlace(token);
		}

		@Override
		public String getToken(DashBoardPlace place) {
			return place.getToken();
		}
	}
	
	private String token;
	
	public DashBoardPlace(String token) {
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
		DashBoardPlace other = (DashBoardPlace) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
}
