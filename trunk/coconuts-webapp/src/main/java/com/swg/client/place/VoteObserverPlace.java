package com.swg.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class VoteObserverPlace extends Place {
	@Prefix("voteobserver")
	public static class Tokenizer implements PlaceTokenizer<VoteObserverPlace> {
		@Override
		public VoteObserverPlace getPlace(String token) {
			return null;
		}

		@Override
		public String getToken(VoteObserverPlace place) {
			return null;
		}
	}
	
	private String token;
	public VoteObserverPlace(String token) {
		this.token = token.toLowerCase();
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
		VoteObserverPlace other = (VoteObserverPlace) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
}
