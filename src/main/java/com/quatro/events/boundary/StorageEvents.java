package com.quatro.events.boundary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quatro.events.entity.Event;
import one.microstream.reference.Lazy;

public class StorageEvents {
  
    private Map<String, Lazy<List<Event>>> postalCodeEvents = new HashMap<>();

    public Map<String, Lazy<List<Event>>> getPostalCodeEvents() {
        return postalCodeEvents;
    }

    public void setPostalCodeEvents(Map<String, Lazy<List<Event>>> postalCodeEvents) {
        this.postalCodeEvents = postalCodeEvents;
    }

}
