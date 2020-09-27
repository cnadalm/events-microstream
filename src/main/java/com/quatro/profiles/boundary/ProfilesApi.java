package com.quatro.profiles.boundary;

import java.util.Collection;

import com.quatro.profiles.control.ProfilesCreation;
import com.quatro.profiles.control.ProfilesRead;
import com.quatro.profiles.entity.Profile;
import com.quatro.profiles.entity.ProfilesRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ProfilesApi implements ProfilesCreation, ProfilesRead {

    @Inject
    ProfilesRepository repository;

    @GET
    @Override
    public Collection<Profile> all() {
        return repository.all();
    }

    @GET
    @Path("/{profileId}")
    @Override
    public Profile find(@PathParam("profileId") String profileId) {
        return repository.find(profileId).orElseThrow(NotFoundException::new);
    }

    @POST
    @Override
    public void create(JsonObject json) {
        Profile profile = new Profile(json.getString("name"), json.getString("avatar"));
        repository.create(profile);
    }

}
