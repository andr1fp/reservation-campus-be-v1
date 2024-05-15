package com.enigmacamp.reservationcampus.services.impl.facilities;

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
        Availability availability = Availability.builder().id(places.getAvailabilityId()).build();
        TypeFacilities typeFacilities = TypeFacilities.builder().id(places.getTypefacilityId()).build();
        Places place = new Places();
        place.setId(places.getId());
        place.setAvailability(availability);
        place.setFacility(typeFacilities);
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
    public PlaceRequest updatePlaces(PlaceRequest places) {
        if (placesRepository.findById(places.getId()).isPresent()) {
            Availability availability = Availability.builder().id(places.getAvailabilityId()).build();
            TypeFacilities typeFacilities = TypeFacilities.builder().id(places.getTypefacilityId()).build();
            Places place = new Places();
            place.setId(places.getId());
            place.setAvailability(availability);
            place.setFacility(typeFacilities);
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
}

