package com.enigmacamp.reservationcampus.service;

import com.enigmacamp.reservationcampus.model.request.VehicleRequest;
import com.enigmacamp.reservationcampus.model.facilities.Vehicles;

import java.util.List;

public interface VehicleService {

   VehicleRequest saveVehicle (VehicleRequest vehicles);
   Vehicles getVehicleById(String id);
   VehicleRequest updateVehicle(VehicleRequest vehicles);
   void deleteVehicle(String id);
   List<Vehicles> getAllVehicles();

}
