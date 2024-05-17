package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.entity.Facility;
import com.enigmacamp.reservationcampus.model.entity.constant.Availability;
import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.model.request.FacilityRequest;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.model.response.FacilityResponse;
import com.enigmacamp.reservationcampus.services.FacilityService;
import com.enigmacamp.reservationcampus.services.constant.TypeFacilitiesService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;
import com.enigmacamp.reservationcampus.utils.constant.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping(APIPath.API + APIPath.FACILITIES)
@RequiredArgsConstructor
public class FacilityController {

    String uploadDir = "assets/images/facilities/";
    Path uploadPath = Paths.get(uploadDir);

    private final FacilityService facilityService;
    private final TypeFacilitiesService typeFacilitiesService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse<FacilityRequest>> createFacility(@RequestBody FacilityRequest facility){
        String message = String.format(Message.MESSAGE_INSERT);
        FacilityRequest facilityRequest = facilityService.saveFacility(facility);

        CommonResponse<FacilityRequest> response = CommonResponse.<FacilityRequest>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(facilityRequest)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<CommonResponse<FacilityResponse>> updateFacility(@RequestBody FacilityRequest facility) {
        String message = String.format(Message.MESSAGE_UPDATE);
        FacilityResponse facilityResponse = facilityService.updateFaciliyRes(facility);

        CommonResponse<FacilityResponse> response = CommonResponse.<FacilityResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(facilityResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Facility>> updateFacility(@RequestBody Facility facility){
        String message = String.format(Message.MESSAGE_UPDATE);
        Facility result = facilityService.updateFacility(facility);

        CommonResponse<Facility> response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(result)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }



    @GetMapping
    public ResponseEntity<CommonResponse<List<Facility>>> getAllFacilities(){
        String message = String.format(Message.MESSAGE_READ);
        List<Facility> result = facilityService.getAllFacilities();

        CommonResponse<List<Facility>> response = CommonResponse.<List<Facility>>builder()
               .statusCode(HttpStatus.OK.value())
               .message(message)
               .data(result)
               .build();
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Facility>> deleteFacility(@PathVariable String id){
        String message = String.format(Message.MESSAGE_DELETE);
        facilityService.deleteFacility(id);

        CommonResponse<Facility> response = CommonResponse.<Facility>builder()
               .statusCode(HttpStatus.OK.value())
               .message(message)
               .build();
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(response);
    }

    @PostMapping
    public ResponseEntity<FacilityRequest> uploadFacility(@RequestParam("name") String name,
                                                         @RequestParam("picture") MultipartFile picture,
                                                         @RequestParam("quantity") Integer quantity,
                                                         @RequestParam("information") String information,
                                                         @RequestParam("price") Integer price,
                                                         @RequestParam("id_facility") String id_facility,
                                                         @RequestParam("id_availability") String id_availability) throws IOException {
        FacilityRequest facilityRequest = new FacilityRequest();
        facilityRequest.setName(name);
        facilityRequest.setInformation(information);
        facilityRequest.setPrice(price);
        facilityRequest.setQuantity(quantity);
        facilityRequest.setTypeFacilities(id_facility);
        facilityRequest.setAvailability(id_availability);
        String originalFilename = picture.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        if(!extension.equals(".jpg")){
            extension = ".jpg";
        }
        String newFileName = name + extension;
        facilityRequest.setPicture(picture.getName());
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(newFileName);
        Files.copy(picture.getInputStream(), filePath);

        return new ResponseEntity<>(facilityService.saveFacility(facilityRequest), HttpStatus.CREATED);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<FacilityRequest> editFacility(@PathVariable String id,
                                                        @RequestParam("name") String name,
                                                          @RequestParam("picture") MultipartFile picture,
                                                          @RequestParam("quantity") Integer quantity,
                                                          @RequestParam("information") String information,
                                                          @RequestParam("price") Integer price,
                                                          @RequestParam("id_facility") String id_facility,
                                                          @RequestParam("id_availability") String id_availability) throws IOException {
        FacilityRequest facilityRequest = new FacilityRequest();
        facilityRequest.setName(name);
        facilityRequest.setInformation(information);
        facilityRequest.setPrice(price);
        facilityRequest.setQuantity(quantity);
        facilityRequest.setTypeFacilities(id_facility);
        facilityRequest.setAvailability(id_availability);

        if (picture != null && !picture.isEmpty()) {
            String originalFilename = picture.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            if(!extension.equals(".jpg")){
                extension = ".jpg";
            }
            String newFileName = name + extension;

            facilityRequest.setPicture(newFileName); // Perbarui nama gambar

            // Simpan gambar yang baru
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(picture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING); // Ganti gambar yang ada
        }
        facilityService.saveFacility(facilityRequest);

        // Beri respons sukses
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping(APIPath.BYNAME)
//    public ResponseEntity<CommonResponse<List<Facility>>> getFacilityByName(@RequestParam("name") String name){
//        String message = String.format(Message.MESSAGE_READ);
//        List<Facility> result = facilityService.getFacilityByName(name);
//        CommonResponse<List<Facility>> response = CommonResponse.<List<Facility>>builder()
//                .statusCode(HttpStatus.OK.value())
//                .message(message)
//                .data(result)
//                .build();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(response);
//    }
//
//    @GetMapping(APIPath.BYTYPE)
//    public ResponseEntity<CommonResponse<List<Facility>>> getFacilitiesByType(@RequestParam("type") String type){
//        String message = String.format(Message.MESSAGE_READ);
//        List<Facility> result = facilityService.getFacilitiesByType(type);
//        CommonResponse<List<Facility>> response = CommonResponse.<List<Facility>>builder()
//                .statusCode(HttpStatus.OK.value())
//                .message(message)
//                .data(result)
//                .build();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(response);
//    }
//
//    @GetMapping(APIPath.BYAVAILABILITY)
//    public ResponseEntity<CommonResponse<List<Facility>>> getFacilitiesByAvailability(@RequestParam("availability") String availability){
//        String message = String.format(Message.MESSAGE_READ);
//        List<Facility> result = facilityService.getFacilitiesByAvailability(availability);
//        CommonResponse<List<Facility>> response = CommonResponse.<List<Facility>>builder()
//                .statusCode(HttpStatus.OK.value())
//                .message(message)
//                .data(result)
//                .build();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(response);
//    }

}


