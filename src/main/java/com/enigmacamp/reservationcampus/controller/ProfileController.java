package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.entity.Profile;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.services.AuthService;
import com.enigmacamp.reservationcampus.services.ProfileService;
import com.enigmacamp.reservationcampus.services.UserService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIPath.API + APIPath.AUTH + APIPath.PROFILE)
@RequiredArgsConstructor
public class ProfileController {

    private final AuthService authService;
    private final ProfileService profileService;
    private final UserService userService;

    @PutMapping
    public ResponseEntity<CommonResponse<Profile>> updateProfile(@RequestBody Profile updateProfile){
        String message = String.format(Message.MESSAGE_UPDATE);
        Profile result = profileService.updateProfile(updateProfile);

        CommonResponse<Profile> response = CommonResponse.<Profile>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Profile>> getProfileById(@PathVariable String id){
        String message = String.format(Message.MESSAGE_READ, id);
        Profile result = profileService.getProfileByUserId(id);

        CommonResponse<Profile> response = CommonResponse.<Profile>builder()
               .statusCode(HttpStatus.OK.value())
               .message(message)
               .data(result)
               .build();
        return ResponseEntity.status(HttpStatus.OK)
               .contentType(MediaType.APPLICATION_JSON)
               .body(response);
    }

}
