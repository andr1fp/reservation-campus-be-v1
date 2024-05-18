package com.enigmacamp.reservationcampus.services;

import com.enigmacamp.reservationcampus.model.request.AuthRequestStudent;
import com.enigmacamp.reservationcampus.model.response.RegisterResponse;

public interface AdminService {

    RegisterResponse registerStudent(AuthRequestStudent authRequestStudent);

    void deleteUser(String id);
}
