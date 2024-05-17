package com.enigmacamp.reservationcampus.controller;


import com.enigmacamp.reservationcampus.model.request.AuthRequestStudent;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.model.response.RegisterResponse;
import com.enigmacamp.reservationcampus.services.AdminService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIPath.API + APIPath.AUTH + APIPath.ADMIN)
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping(APIPath.STUDENT)
//    @PreAuthorize("hasAnyRole({'ROLE_ADMIN'})")
    public ResponseEntity<?> registerStudent(@RequestBody AuthRequestStudent authRequestStudent){
        RegisterResponse registerResponse = adminService.registerStudent(authRequestStudent);

        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register new Student")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        adminService.deleteStudent(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
