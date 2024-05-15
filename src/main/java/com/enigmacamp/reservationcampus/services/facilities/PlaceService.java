package com.enigmacamp.reservationcampus.services.facilities;

import com.enigmacamp.reservationcampus.model.facilities.Places;
import com.enigmacamp.reservationcampus.model.request.PlaceRequest;

import java.util.List;

public interface PlaceService {
    PlaceRequest savePlaces(PlaceRequest places);
    Places getPlacesById(String id);
    List<Places> getAllPlaces();
    PlaceRequest updatePlaces(PlaceRequest places);
    void deletePlaces(String id);

    void uploadPlace(PlaceRequest places);
}
