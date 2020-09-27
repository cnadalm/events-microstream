package com.quatro.profiles.boundary;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.quatro.profiles.entity.Profile;
import com.quatro.profiles.entity.ProfilesRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class StorageProfilesRepository implements ProfilesRepository {
  
    @Inject
    @ConfigProperty(name = "storage.path.events", defaultValue = "../storage-profiles")
    String storagePath;

    private EmbeddedStorageManager storage = null;

    @Override
    public Collection<Profile> all() {
        return getRoot().getProfiles().values();
    }

    @Override
    public Optional<Profile> find(String profileId) {
        return Optional.ofNullable(getRoot().getProfiles().get(profileId));
    }

    @Override
    public void create(Profile profile) {
        Map<String, Profile> profiles = getRoot().getProfiles();
        profiles.put(profile.getId(), profile);
        storage.store(profiles);
    }

    private StorageProfiles getRoot() {
        if (Objects.isNull(storage)) {
            this.storage = EmbeddedStorage.start(Paths.get(storagePath));
            if (Objects.isNull(storage.root())) {
                storage.setRoot(new StorageProfiles());
                storage.storeRoot();
            }
//            StorageRestService service = StorageRestServiceResolver.resolve(storage);
//            service.start();
        }
        return (StorageProfiles) storage.root();
    }

}
