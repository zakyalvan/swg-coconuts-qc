package com.swg.web.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * Declares all place available in this application.
 * 
 * @author zakyalvan
 */
@WithTokenizers({
	DashBoardPlace.Tokenizer.class,
	SmsServicePlace.Tokenizer.class, 
	VoteCountingPlace.Tokenizer.class,
	VoteObserverPlace.Tokenizer.class
})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
