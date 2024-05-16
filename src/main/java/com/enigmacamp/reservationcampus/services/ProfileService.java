package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile saveProfile(Profile profile);

    List<Profile> getAllProfiles();

//    Profile getProfileById(String id);

    List<Profile> getProfileByName(String name);

    Profile updateProfile(String id, Profile profile);

    void deleteProfile(String id);

    Profile getProfileByUserId(String userId);


}
