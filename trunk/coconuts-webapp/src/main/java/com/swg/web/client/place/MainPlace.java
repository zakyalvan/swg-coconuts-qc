package com.swg.web.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.swg.web.client.presenter.impl.DashBoardPresenter;

public class MainPlace extends Place {
	@Prefix("main")
	public static class Tokenizer implements PlaceTokenizer<MainPlace> {
		@Override
		public MainPlace getPlace(String token) {
			return new MainPlace(token);
		}
		@Override
		public String getToken(MainPlace place) {
			return place.getName();
		}
	}
	
	public static final MainPlace DEFAULT = new MainPlace(DashBoardPresenter.NAME);
	
	/**
	 * Name ini menunjukan bagian mana dari view yang akan ditampilin.
	 */
	private final String name;
	public MainPlace(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "MainPlace [name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.toLowerCase().hashCode());
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
		MainPlace other = (MainPlace) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}
}
