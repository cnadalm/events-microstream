package com.quatro.profiles.control;

import java.util.Collection;

import com.quatro.profiles.entity.Profile;

public interface ProfilesRead {
    
    Collection<Profile> all();
    
    Profile find(String profileId);
    
}
