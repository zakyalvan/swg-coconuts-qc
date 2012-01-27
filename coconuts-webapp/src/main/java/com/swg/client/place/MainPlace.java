package com.swg.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MainPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<MainPlace> {
		@Override
		public MainPlace getPlace(String token) {
			return null;
		}

		@Override
		public String getToken(MainPlace place) {
			return null;
		}	
	}
	
	/**
	 * Name ini menunjukan bagian mana dari view yang akan ditampilin.
	 */
	private String name;

	public MainPlace(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
