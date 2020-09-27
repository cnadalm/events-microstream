package com.quatro.events.control;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.quatro.events.entity.Event;

public interface EventsRead {
    
    Map<String, Collection<Event>> all(List<String> postalCode);
    
    Event find(String eventId);
    
}
