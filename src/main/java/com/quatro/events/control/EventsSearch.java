package com.quatro.events.control;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.quatro.events.entity.Event;

public interface EventsSearch {
    
    Map<String, Collection<Event>> search(List<String> postalCodes);
    
}
