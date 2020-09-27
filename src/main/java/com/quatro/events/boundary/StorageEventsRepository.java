package com.quatro.events.boundary;

import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.quatro.events.entity.Event;
import com.quatro.events.entity.EventsRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import one.microstream.reference.Lazy;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class StorageEventsRepository implements EventsRepository {
  
    @Inject
    @ConfigProperty(name = "storage.path.events", defaultValue = "../storage-events")
    String storagePath;

    private EmbeddedStorageManager storage = null;
    
    @Override
    public Collection<String> postalCodes() {
        return getRoot().getPostalCodeEvents().keySet();
    }

    @Override
    public Collection<Event> all(String postalCode) {
        if(getRoot().getPostalCodeEvents().containsKey(postalCode)) {
            return getRoot().getPostalCodeEvents().get(postalCode).get();
        }
        return new ArrayList<>();
    }
    
    @Override
    public Optional<Event> find(String postalCode, String eventId) {
        if(getRoot().getPostalCodeEvents().containsKey(postalCode)) {
            return getRoot().getPostalCodeEvents().get(postalCode).get().stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst();
        }
        return Optional.empty();
    }

    @Override
    public void create(String postalCode, Event event) {
        Map<String, Lazy<List<Event>>> postalCodeEvents = getRoot().getPostalCodeEvents();
        if(postalCodeEvents.containsKey(postalCode)) {
            Lazy<List<Event>> events = postalCodeEvents.get(postalCode);
            events.get().add(event);
            storage.store(events);
        } else {
            Lazy<List<Event>> events = Lazy.Reference(new ArrayList<>());
            events.get().add(event);
            postalCodeEvents.put(postalCode, events);
            storage.store(postalCodeEvents);
        }
    }
    
    @Override
    public void update(Event event) {
        event.setUpdatedAt(Instant.now());
        storage.store(event);
    }

    private StorageEvents getRoot() {
        if (Objects.isNull(storage)) {
            this.storage = EmbeddedStorage.start(Paths.get(storagePath));
            if (Objects.isNull(storage.root())) {
                storage.setRoot(new StorageEvents());
                storage.storeRoot();
            }
//            StorageRestService service = StorageRestServiceResolver.resolve(storage);
//            service.start();
        }
        return (StorageEvents) storage.root();
    }

}
