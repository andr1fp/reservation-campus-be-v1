package com.enigmacamp.reservationcampus.services.impl.facilities;

import com.enigmacamp.reservationcampus.model.request.VehicleRequest;
import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.model.facilities.Vehicles;
import com.enigmacamp.reservationcampus.repository.VehicleRepository;
import com.enigmacamp.reservationcampus.services.facilities.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private  final VehicleRepository vehicleRepository;

    @Override
    public VehicleRequest saveVehicle(VehicleRequest vehicles) {
        Availability availability = Availability.builder().id(vehicles.getId_availability()).build();
        TypeFacilities typeFacilities = TypeFacilities.builder().id(vehicles.getId_facility()).build();
        Vehicles vehicle = new Vehicles();

        vehicle.setPicture(vehicles.getPicture());
        vehicle.setName(vehicles.getName());
        vehicle.setDescription(vehicles.getDescription());
        vehicle.setPrice(vehicles.getPrice());
        vehicle.setNoPolice(vehicles.getNoPolice());


        vehicle.setAvailability(availability);
        vehicle.setFacility(typeFacilities);

        vehicleRepository.save(vehicle);

        return vehicles;
    }

    @Override
    public Vehicles getVehicleById(String id) {
        if(vehicleRepository.findById(id).isPresent()){
            return vehicleRepository.findById(id).get();
        }else{
            throw new RuntimeException("Vehicle not found");
        }
    }

    @Override
    public VehicleRequest updateVehicle(VehicleRequest vehicles) {
        if (vehicleRepository.findById(vehicles.getId()).isPresent()) {
            Availability availability = Availability.builder().id(vehicles.getId_availability()).build();
            TypeFacilities typeFacilities = TypeFacilities.builder().id(vehicles.getId_facility()).build();
            Vehicles vehicle = new Vehicles();

            vehicle.setId(vehicles.getId());
            vehicle.setPicture(vehicles.getPicture());
            vehicle.setName(vehicles.getName());
            vehicle.setDescription(vehicles.getDescription());
            vehicle.setPrice(vehicles.getPrice());
            vehicle.setNoPolice(vehicles.getNoPolice());

            vehicle.setAvailability(availability);
            vehicle.setFacility(typeFacilities);

            vehicleRepository.save(vehicle);
            return vehicles;
        }else{
            throw new RuntimeException("Vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String id) {
        if(vehicleRepository.findById(id).isPresent()){
            vehicleRepository.deleteById(id);
        }else{
            throw new RuntimeException("Vehicle not found");
        }
    }

    @Override
    public List<Vehicles> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Page<Vehicles> getAllVehiclesPages(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }
}
