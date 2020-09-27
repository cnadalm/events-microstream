package com.quatro.profiles.entity;

import java.util.UUID;

public class Profile {

    private final String id;
    private final String name;
    private final String avatar;

    public Profile(String name, String avatar) {
        super();
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Profile{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", avatar=").append(avatar);
        sb.append('}');
        return sb.toString();
    }
    
}
