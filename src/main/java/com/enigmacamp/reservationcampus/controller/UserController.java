package com.enigmacamp.reservationcampus.controller;


import com.enigmacamp.reservationcampus.model.entity.Profile;
import com.enigmacamp.reservationcampus.model.request.AuthRequestStudent;
import com.enigmacamp.reservationcampus.services.ProfileService;
import com.enigmacamp.reservationcampus.services.UserService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIPath.API + APIPath.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @PutMapping(APIPath.STUDENT + "/{id}")
    public ResponseEntity<Profile> editUserProfileById(@PathVariable String id, @RequestBody AuthRequestStudent authRequestStudent) {
        Profile updatedProfile = profileService.updateProfileById(id, authRequestStudent);
        return ResponseEntity.ok(updatedProfile);
    }

}
