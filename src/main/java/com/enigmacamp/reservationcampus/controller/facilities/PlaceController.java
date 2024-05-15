package com.enigmacamp.reservationcampus.controller.facilities;

import com.enigmacamp.reservationcampus.model.facilities.Places;
import com.enigmacamp.reservationcampus.model.request.PlaceRequest;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.services.facilities.PlaceService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.MessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.API + APIPath.PLACES)
public class PlaceController {
    private final PlaceService placesService;

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN'})")
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

    @PutMapping("{/id}")
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN'})")
    public PlaceRequest updatePlaces(@PathVariable String id, @RequestBody PlaceRequest places){
        return placesService.savePlaces(places);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN'})")
    public ResponseEntity<Places> deletePlaces(@PathVariable String id){
        for (Places places : placesService.getAllPlaces()){
            if(places.getId().equals(id)){
                placesService.deletePlaces(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/upload")
    public void uploadPlace(@RequestParam("name") String name,
                                                              @RequestParam("picture") MultipartFile picture,
                                                              @RequestParam("price") Integer price,
                                                              @RequestParam("capacity") Integer capacity,
                                                              @RequestParam("description") String description,
                                                              @RequestParam("availability") String availability,
                                                              @RequestParam("typefacility") String typefacility) {
        PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.setName(name);
        placeRequest.setPrice(price);
        placeRequest.setCapacity(capacity);
        placeRequest.setDescription(description);
        placeRequest.setId_availability(availability);
        placeRequest.setId_facility(typefacility);
        placeRequest.setPicture(picture.getOriginalFilename());
        placesService.uploadPlace(placeRequest);
    }
}

