package com.enigmacamp.reservationcampus.service;

import com.enigmacamp.reservationcampus.model.entity.Profile;
import com.enigmacamp.reservationcampus.model.request.AuthRequestStudent;
import com.enigmacamp.reservationcampus.model.response.RegisterResponse;

import java.util.List;

public interface AdminService {

    RegisterResponse registerStudent(AuthRequestStudent authRequestStudent);

}
