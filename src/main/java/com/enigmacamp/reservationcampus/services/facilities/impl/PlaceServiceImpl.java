package com.enigmacamp.reservationcampus.services.facilities.impl;

import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.model.facilities.Places;
import com.enigmacamp.reservationcampus.model.request.PlaceRequest;
import com.enigmacamp.reservationcampus.repository.PlaceRepository;
import com.enigmacamp.reservationcampus.services.facilities.PlaceService;
import com.enigmacamp.reservationcampus.utils.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placesRepository;

    @Override
    public PlaceRequest savePlaces(PlaceRequest places) {
        Availability availability = Availability.builder().id(places.getId_availability()).build();
        TypeFacilities typeFacilities = TypeFacilities.builder().id(places.getId_facility()).build();
        Places place = new Places();
        place.setId(places.getId());
        place.setAvailability(availability);
        place.setFacilities(typeFacilities);
        place.setName(places.getName());
        place.setPrice(places.getPrice());
        placesRepository.save(place);
        return places;
    }

    @Override
    public Places getPlacesById(String id) {
        if (placesRepository.findById(id).isPresent()) {
            return placesRepository.findById(id).get();
        }
        else {
            throw new DataNotFoundException("Data Tidak Ditemukan");
        }
    }

    @Override
    public List<Places> getAllPlaces() {
        return placesRepository.findAll();
    }

    @Override
    public List<Places> findPlacesByName(String name) {
        return placesRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public PlaceRequest updatePlaces(PlaceRequest places) {
        if (placesRepository.findById(places.getId()).isPresent()) {
            Availability availability = Availability.builder().id(places.getId_availability()).build();
            TypeFacilities typeFacilities = TypeFacilities.builder().id(places.getId_facility()).build();
            Places place = new Places();
            place.setId(places.getId());
            place.setAvailability(availability);
            place.setFacilities(typeFacilities);
            place.setName(places.getName());
            place.setPrice(places.getPrice());
            placesRepository.save(place);
            return places;
        } else {
            throw new DataNotFoundException("Data Tidak Ditemukan");
        }
    }

    @Override
    public void deletePlaces(String id) {
        if (placesRepository.findById(id).isPresent()) {
            placesRepository.deleteById(id);
        }
        else {
            throw new DataNotFoundException("Data Tidak Ditemukan");
        }
    }

    @Override
    public void uploadPlace(PlaceRequest places) {
        Availability availability = Availability.builder().id(places.getId_availability()).build();
        TypeFacilities typeFacilities = TypeFacilities.builder().id(places.getId_facility()).build();
        Places place = new Places();
        place.setDescription(places.getDescription());
        place.setCapacity(places.getCapacity());
        place.setPicture(places.getPicture());
        place.setAvailability(availability);
        place.setFacilities(typeFacilities);
        place.setName(places.getName());
        place.setPrice(places.getPrice());
        placesRepository.save(place);
    }
}

