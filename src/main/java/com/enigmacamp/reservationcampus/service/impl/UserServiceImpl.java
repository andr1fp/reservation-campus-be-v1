package com.enigmacamp.reservationcampus.service.impl;

import com.enigmacamp.reservationcampus.model.entity.AppUser;
import com.enigmacamp.reservationcampus.model.entity.User;
import com.enigmacamp.reservationcampus.repository.UserRepository;
import com.enigmacamp.reservationcampus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public AppUser loadUserById(String id) { //method untuk verif token JWT
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid credential User"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().getName())
                .build();
    }

    @Override //method untuk memverifikasi USERNAME/EMAIL
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid credential User"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().getName())
                .build();
    }

}
