package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.request.AuthRequestAdmin;
import com.enigmacamp.reservationcampus.model.request.AuthRequestLogin;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.model.response.LoginResponse;
import com.enigmacamp.reservationcampus.model.response.RegisterResponse;
import com.enigmacamp.reservationcampus.service.AuthService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIPath.API + APIPath.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //REGIST
    @PostMapping(APIPath.REGIST + APIPath.ADMIN)
    public ResponseEntity<?> register(@RequestBody AuthRequestAdmin authRequestAdmin){
        RegisterResponse registerResponse = authService.registerAdmin(authRequestAdmin);

        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register new Admin")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //LOGIN
    @PostMapping(APIPath.LOGIN)
    public ResponseEntity<?> loginCustomer(@RequestBody AuthRequestLogin request){
        LoginResponse loginResponse = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("Success Login")
                .statusCode(HttpStatus.OK.value())
                .data(loginResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
