package com.quatro.events.boundary;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.quatro.events.control.EventsCreation;
import com.quatro.events.control.EventsJoin;
import com.quatro.events.control.EventsRead;
import com.quatro.events.entity.Event;
import com.quatro.events.entity.EventAddress;
import com.quatro.events.entity.EventsRepository;
import com.quatro.profiles.boundary.ProfilesApi;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class EventsApi implements EventsCreation, EventsRead, EventsJoin {

    @Inject
    EventsRepository repository;
    
    @Inject
    ProfilesApi profilesApi;

    @GET
    @Override
    public Map<String, Collection<Event>> all(@QueryParam("postalCode") List<String> postalCodes) {
        Collection<String> locations = Objects.isNull(postalCodes) || postalCodes.isEmpty() ? repository.postalCodes() : postalCodes;
        return locations.stream()
              .map(postalCode -> Map.entry(postalCode, repository.all(postalCode)))
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @GET
    @Path("/{eventId}")
    @Override
    public Event find(@PathParam("eventId") String eventId) {
        String postalCode = getPostalCode(eventId);
        return repository.find(postalCode, eventId).orElseThrow(() -> new NotFoundException());
    }

    @POST
    @Override
    public void create(@QueryParam("postalCode") String postalCode, @QueryParam("organizerId") String organizerId, JsonObject json) {
        // TODO Validate postalCode
        profilesApi.find(organizerId); // validate profile
        
        EventAddress address = EventAddress.of(json);
        Event event = new Event(Objects.requireNonNull(postalCode),
            json.getString("title"),
            json.getString("description"),
            LocalDate.now(),
            LocalDate.now(),
            LocalTime.NOON,
            LocalTime.NOON.plusHours(3),
            address,
            organizerId);
            
        repository.create(postalCode, event);
    }

    @POST
    @Path("/{eventId}/register")
    @Override
    public void register(@PathParam("eventId") String eventId, @QueryParam("profileId") String profileId) {
        profilesApi.find(profileId); // validate profile
        
        String postalCode = getPostalCode(eventId);
        Event event = repository.find(postalCode, eventId).orElseThrow(NotFoundException::new);
        event.getJoinedProfileIds().add(profileId);
        repository.update(event);
    }
    
    @DELETE
    @Path("/{eventId}/register")
    @Override
    public void unregister(@PathParam("eventId") String eventId, @QueryParam("profileId") String profileId) {
        profilesApi.find(profileId); // validate profile
        
        String postalCode = getPostalCode(eventId);
        Event event = repository.find(postalCode, eventId).orElseThrow(NotFoundException::new);
        event.getJoinedProfileIds().remove(profileId);
        repository.update(event);
    }

    private String getPostalCode(String eventId) {
        return eventId.substring(0, eventId.indexOf("-"));
    }

}
