package com.swg.web.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Place yang nunjukin aktivitas reporting.
 * 
 * @author zakyalvan
 */
public class ReportingPlace extends Place {
	@Prefix("reporting")
	public static class Tokenizer implements PlaceTokenizer<ReportingPlace> {
		@Override
		public ReportingPlace getPlace(String token) {
			return new ReportingPlace(token);
		}
		@Override
		public String getToken(ReportingPlace place) {
			return place.getName();
		}
	}
	
	private final String name;
	public ReportingPlace(String name) {
		this.name = name.toLowerCase();
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
		ReportingPlace other = (ReportingPlace) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}
}
