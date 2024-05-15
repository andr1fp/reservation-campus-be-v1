package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.entity.AppUser;
import com.enigmacamp.reservationcampus.model.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserById(String id);

    User updateUser(String id, User user);

    void deleteUser(String id);


}
