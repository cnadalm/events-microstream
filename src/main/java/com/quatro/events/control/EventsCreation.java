package com.quatro.events.control;

import javax.json.JsonObject;

public interface EventsCreation {
    
    void create(String postalCode, String organizerId, JsonObject json);
    
}
