package com.quatro.events.control;

public interface EventsSubscription {
    
    void join(String eventId, String participantId);
   
}
