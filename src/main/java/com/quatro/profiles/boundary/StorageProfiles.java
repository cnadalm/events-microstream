package com.quatro.profiles.boundary;

import java.util.HashMap;
import java.util.Map;

import com.quatro.profiles.entity.Profile;

public class StorageProfiles {

    private Map<String, Profile> profiles = new HashMap<>();

    public Map<String, Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Map<String, Profile> profiles) {
        this.profiles = profiles;
    }

}
