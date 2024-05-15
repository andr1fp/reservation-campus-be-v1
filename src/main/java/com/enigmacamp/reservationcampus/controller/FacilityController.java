package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.services.FacilityService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIPath.API + APIPath.FACILITIES)
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @PostMapping
    public ResponseEntity<CommonResponse<Facility>> createFacility(@RequestBody Facility facility){
        String message = String.format(Message.MESSAGE_INSERT);
        Facility result = facilityService.saveFacility(facility);

        CommonResponse<Facility> response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(result)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Facility>> updateFacility(@RequestBody Facility facility){
        String message = String.format(Message.MESSAGE_UPDATE);
        Facility result = facilityService.updateFacility(facility);

        CommonResponse<Facility> response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(result)
                .build();
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Facility>>> getAllFacilities(){
        String message = String.format(Message.MESSAGE_READ);
        List<Facility> result = facilityService.getAllFacilities();

        CommonResponse<List<Facility>> response = CommonResponse.<List<Facility>>builder()
               .statusCode(HttpStatus.OK.value())
               .message(message)
               .data(result)
               .build();
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Facility>> deleteFacility(@PathVariable String id){
        String message = String.format(Message.MESSAGE_DELETE);
        facilityService.deleteFacility(id);

        CommonResponse<Facility> response = CommonResponse.<Facility>builder()
               .statusCode(HttpStatus.OK.value())
               .message(message)
               .build();
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(response);
    }
}


