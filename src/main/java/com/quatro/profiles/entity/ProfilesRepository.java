package com.quatro.profiles.entity;

import java.util.Collection;
import java.util.Optional;


public interface ProfilesRepository {
    
    public Collection<Profile> all();
    
    public Optional<Profile> find(String profileId);
    
    public void create(Profile profile);
    
}
