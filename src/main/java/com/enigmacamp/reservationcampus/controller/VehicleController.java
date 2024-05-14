package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.request.VehicleRequest;
import com.enigmacamp.reservationcampus.model.facilities.Vehicles;
import com.enigmacamp.reservationcampus.service.VehicleService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.BASE_PATH + APIPath.VEHICLES)
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping
    public List<Vehicles> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicles getVehicleById(@PathVariable String id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    public VehicleRequest saveVehicle(@RequestBody VehicleRequest vehicles) {
        return vehicleService.saveVehicle(vehicles);
    }

    @PutMapping
    public VehicleRequest updateVehicle(@RequestBody VehicleRequest vehicles) {
        return vehicleService.updateVehicle(vehicles);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
    }
}
