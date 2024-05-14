package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.request.VehicleRequest;
import com.enigmacamp.reservationcampus.model.facilities.Vehicles;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.model.response.PageResponseWrapper;
import com.enigmacamp.reservationcampus.service.VehicleService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.BASE_PATH + APIPath.VEHICLES)
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<Vehicles>>> getAllVehicles(@RequestParam(name="page", defaultValue = "0") Integer pageNumber,
                                                                         @RequestParam(name="size", defaultValue = "3") Integer pageSize,
                                                                         @RequestParam(name="sortDir", defaultValue = "ASC") String sortDir) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDir), "name");
        Page <Vehicles> page = vehicleService.getAllVehiclesPages(pageable);


        CommonResponse<List<Vehicles>> commonResponse = new CommonResponse<>();
        List<Vehicles> vehiclesResponse = page.getContent();

        if (vehiclesResponse.isEmpty()) {
            commonResponse.setData(null);
            commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            commonResponse.setMessage("No vehicles found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
        } else {
            commonResponse.setData(vehiclesResponse);
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setMessage("Success Get All Vehicles");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Vehicles>> getVehicleById(@PathVariable String id) {
        CommonResponse<Vehicles> commonResponse = new CommonResponse<>();
        Vehicles vehicleResponse = vehicleService.getVehicleById(id);
        if (vehicleResponse == null) {
            commonResponse.setData(null);
            commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            commonResponse.setMessage("No vehicle found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
        } else {
            commonResponse.setData(vehicleResponse);
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setMessage("Success Get Vehicle By Id");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }
    }


    @PostMapping
    public ResponseEntity<CommonResponse<VehicleRequest>> saveVehicle(@RequestBody VehicleRequest vehicles) {
        CommonResponse<VehicleRequest> commonResponse = new CommonResponse<>();
        VehicleRequest vehicleResponse = vehicleService.saveVehicle(vehicles);

        if (vehicleResponse == null) {
            commonResponse.setData(null);
            commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            commonResponse.setMessage("No vehicle found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
        } else {
            commonResponse.setData(vehicleResponse);
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setMessage("Success Save Vehicle");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }

    }

    @PutMapping
    public ResponseEntity<CommonResponse<VehicleRequest>> updateVehicle(@RequestBody VehicleRequest vehicles) {
        CommonResponse<VehicleRequest> commonResponse = new CommonResponse<>();
        VehicleRequest vehicleResponse = vehicleService.updateVehicle(vehicles);

        if(vehicleResponse == null) {
            commonResponse.setData(null);
            commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            commonResponse.setMessage("No vehicle found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
        } else {
            commonResponse.setData(vehicleResponse);
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setMessage("Success Update Vehicle");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteVehicle(@PathVariable String id) {
        CommonResponse<String> commonResponse = new CommonResponse<>();
        if(vehicleService.getVehicleById(id) == null) {
            commonResponse.setData(null);
            commonResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            commonResponse.setMessage("No vehicle found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
        } else {
            vehicleService.deleteVehicle(id);
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setMessage("Success Deleted Data");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }
    }
}
