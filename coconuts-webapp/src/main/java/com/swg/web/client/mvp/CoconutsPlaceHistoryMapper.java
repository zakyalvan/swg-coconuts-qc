package com.swg.web.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.swg.web.client.place.MainPlace;

@WithTokenizers({
	MainPlace.Tokenizer.class
})
public interface CoconutsPlaceHistoryMapper extends PlaceHistoryMapper {}
