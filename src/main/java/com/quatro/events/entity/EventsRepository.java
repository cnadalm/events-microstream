package com.quatro.events.entity;

import java.util.Collection;
import java.util.Optional;


public interface EventsRepository {
    
    public Collection<String> postalCodes();  
  
    public Collection<Event> all(String postalCode);
    
    public Optional<Event> find(String postalCode, String eventId);
    
    public void create(String postalCode, Event event);
    
    public void update(Event event);
    
}
