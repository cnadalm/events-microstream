package com.quatro.events.boundary;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quatro.events.control.EventsSearch;
import com.quatro.events.entity.Event;
import com.quatro.events.entity.EventsRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/search/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class SearchApi implements EventsSearch {

    @Inject
    EventsRepository repository;

    @Override
    public Map<String, Collection<Event>> search(@QueryParam("postalCode") List<String> postalCodes) {
        Map<String, Collection<Event>> result = new HashMap<>();
        postalCodes.forEach(postalCode -> result.put(postalCode, repository.all(postalCode)));
        return result;
    }

}
