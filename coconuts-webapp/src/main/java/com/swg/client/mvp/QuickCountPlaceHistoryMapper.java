package com.swg.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.swg.client.place.MainPlace;

@WithTokenizers({
	MainPlace.Tokenizer.class
	})
public interface QuickCountPlaceHistoryMapper extends PlaceHistoryMapper {}
