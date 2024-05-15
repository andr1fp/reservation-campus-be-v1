package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.services.AvailabilityService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.EAvailability;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.BASE_PATH + APIPath.AVAILABILITY)
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<Availability>>> getAvailabilities() {
        CommonResponse<List<Availability>> commonResponse = new CommonResponse<>();
        List<Availability> availabilities = availabilityService.getAll();

        if(availabilities == null || availabilities.isEmpty()) {
            commonResponse.setData(null);
            commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            commonResponse.setMessage("No availabilities found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
        } else {
            commonResponse.setData(availabilities);
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setMessage("Success Get All Availabilities");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }

    }

    @GetMapping("/{name}")
    public ResponseEntity<CommonResponse<Availability>> getByName(@PathVariable String name) {
        CommonResponse<Availability> commonResponse = new CommonResponse<>();
        Availability availability = availabilityService.getByName(EAvailability.valueOf(name.toUpperCase()));

        if(availability == null) {
            commonResponse.setData(null);
            commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            commonResponse.setMessage("No availability found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
        }else{
            commonResponse.setData(availability);
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setMessage("Success Get Availability By Name");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }

    }

    @PostMapping
    public ResponseEntity<CommonResponse<Availability>> save(@RequestBody Availability availability) {
       CommonResponse<Availability> commonResponse = new CommonResponse<>();
       Availability availabilityResponse = availabilityService.save(availability);

       if(availabilityResponse == null) {
           commonResponse.setData(null);
           commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
           commonResponse.setMessage("No availability found");
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
       }else{
           commonResponse.setData(availabilityResponse);
           commonResponse.setStatusCode(HttpStatus.OK.value());
           commonResponse.setMessage("Success Save Availability");
           return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
       }

    }


    @PutMapping
    public ResponseEntity<CommonResponse<Availability>> update(@RequestBody Availability availability) {
        CommonResponse<Availability> commonResponse = new CommonResponse<>();
        Availability availabilityResponse = availabilityService.update(availability);

        if(availabilityResponse == null) {
            commonResponse.setData(null);
            commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            commonResponse.setMessage("No availability found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
        }else{
            commonResponse.setData(availabilityResponse);
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setMessage("Success Update Availability");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable String id) {
        CommonResponse<String> commonResponse = new CommonResponse<>();
        availabilityService.delete(id);

        commonResponse.setData(id);
        commonResponse.setStatusCode(HttpStatus.OK.value());
        commonResponse.setMessage("Success Delete Availability");
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
