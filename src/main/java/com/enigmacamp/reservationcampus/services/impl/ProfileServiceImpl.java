package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.model.entity.Profile;
import com.enigmacamp.reservationcampus.model.entity.User;
import com.enigmacamp.reservationcampus.repository.ProfileRepository;
import com.enigmacamp.reservationcampus.repository.UserRepository;
import com.enigmacamp.reservationcampus.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;


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
    public Profile updateProfile(Profile profile) {
        Profile profile1 = profileRepository.findById(profile.getId()).get();
        profile1.setNIM(profile.getNIM());
        profile1.setFullName(profile.getFullName());
        profile1.setDateofbirth(profile.getDateofbirth());
        profile1.setPhone(profile.getPhone());
        profile1.setPhoto(profile.getPhoto());
        return profileRepository.save(profile1);

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
