package com.quatro.events.control;

public interface EventsJoin {
    
    void register(String eventId, String participantId);
    
    void unregister(String eventId, String participantId);
   
}
