package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.Profile;
import com.enigmacamp.reservationcampus.repository.ProfileRepository;
import com.enigmacamp.reservationcampus.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    ProfileRepository profileRepository;

    @Autowired
    ProfileServiceImpl(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(String id) {
        return profileRepository.findById(id).get();
    }

    @Override
    public Profile updateProfile(Profile profile, String userId) {
        Profile updatedProfile =  profileRepository.findByUserId(userId);
        if (updatedProfile != null){
            updatedProfile.setFullName(profile.getFullName());
            updatedProfile.setPhone(profile.getPhone());
            updatedProfile.setDateofbirth(profile.getDateofbirth());
            updatedProfile.setPhoto(profile.getPhoto());
            return saveProfile(updatedProfile);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProfile(String id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile getProfileByUserId(String userId) {
        return profileRepository.findByUserId(userId);
    }

    @Override
    public List<Profile> getProfileByName(String name) {
        return profileRepository.findByFullNameContainsIgnoreCase(name);
    }
}
