package com.swg.web.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.swg.web.client.place.MainPlace;
import com.swg.web.client.place.ReportingPlace;

@WithTokenizers({
	MainPlace.Tokenizer.class,
	ReportingPlace.Tokenizer.class
})
public interface CoconutsPlaceHistoryMapper extends PlaceHistoryMapper {}
