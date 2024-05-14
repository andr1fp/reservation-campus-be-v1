package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.facilities.Places;
import com.enigmacamp.reservationcampus.model.request.PlaceRequest;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.services.PlaceService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.MessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.BASE_PATH + APIPath.PLACES)
public class PlaceController {
    private final PlaceService placesService;
    @PostMapping
    public ResponseEntity<CommonResponse<Places>> savePlaces(@RequestBody PlaceRequest places) {
        String message = String.format(MessageConstant.MESSAGE_INSERT, places.getName());
        CommonResponse<Places> response = new CommonResponse<>();
        PlaceRequest result = placesService.savePlaces(places);
        response.setData(response.getData());
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public Places getPlacesById(@PathVariable String id) {
        return placesService.getPlacesById(id);
    }

    @GetMapping
    public List<Places> getAllPlaces() {
        return placesService.getAllPlaces();
    }

    @PutMapping("update/{id}")
    public PlaceRequest updatePlaces(@PathVariable String id, @RequestBody PlaceRequest places){
        return placesService.savePlaces(places);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Places> deletePlaces(@PathVariable String id){
        for (Places places : placesService.getAllPlaces()){
            if(places.getId().equals(id)){
                placesService.deletePlaces(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

