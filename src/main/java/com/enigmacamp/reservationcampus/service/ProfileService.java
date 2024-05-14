package com.enigmacamp.reservationcampus.service;

import com.enigmacamp.reservationcampus.model.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile saveProfile(Profile profile);

    List<Profile> getAllProfiles();

    Profile getProfileById(String id);

    Profile updateProfile(String id, Profile profile);

    void deleteProfile(String id);


}
