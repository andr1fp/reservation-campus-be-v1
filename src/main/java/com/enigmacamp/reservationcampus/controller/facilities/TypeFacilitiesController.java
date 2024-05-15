package com.enigmacamp.reservationcampus.controller.facilities;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import com.enigmacamp.reservationcampus.model.response.CommonResponse;
import com.enigmacamp.reservationcampus.services.facilities.TypeFacilitiesService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import com.enigmacamp.reservationcampus.utils.constant.ETypeFacilities;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.API + APIPath.TYPE_FACILITIES)
public class TypeFacilitiesController {
    private final TypeFacilitiesService typeFacilitiesService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<TypeFacilities>>> getAllTypeFacilities() {
        CommonResponse<List<TypeFacilities>> commonResponse = new CommonResponse<>();
        List<TypeFacilities> typeFacilitiesResponse = typeFacilitiesService.getAll();

        if(typeFacilitiesResponse == null || typeFacilitiesResponse.isEmpty()) {
            commonResponse.setData(null);
            commonResponse.setMessage("Data not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResponse);
        }else{
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setData(typeFacilitiesResponse);
            commonResponse.setMessage("Success get all Type Facilities");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<CommonResponse<TypeFacilities>> getTypeFacilitiesByName(@PathVariable String name) {
        CommonResponse<TypeFacilities> commonResponse = new CommonResponse<>();
        TypeFacilities typeFacilitiesResponse = typeFacilitiesService.getByName(ETypeFacilities.valueOf(name.toUpperCase()));

        if (typeFacilitiesResponse == null) {
            commonResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            commonResponse.setData(null);
            commonResponse.setMessage("Data not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResponse);
        } else {
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setData(typeFacilitiesResponse);
            commonResponse.setMessage("Success get Type Facilities By Name");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }
    }

    @PostMapping
    public ResponseEntity<CommonResponse<TypeFacilities>> saveTypeFacilities(@RequestBody TypeFacilities typeFacilities) {
        CommonResponse<TypeFacilities> commonResponse = new CommonResponse<>();
        TypeFacilities typeFacilitiesResponse = typeFacilitiesService.save(typeFacilities);

        if(typeFacilitiesResponse == null) {
            commonResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            commonResponse.setData(null);
            commonResponse.setMessage("Data not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResponse);
        }else{
            commonResponse.setStatusCode(HttpStatus.OK.value());
            commonResponse.setData(typeFacilitiesResponse);
            commonResponse.setMessage("Success save Type Facilities");
            return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
        }
    }

    @PutMapping
    public ResponseEntity<CommonResponse<TypeFacilities>> updateTypeFacilities(@RequestBody TypeFacilities typeFacilities) {
       CommonResponse<TypeFacilities> commonResponse = new CommonResponse<>();
       TypeFacilities typeFacilitiesResponse = typeFacilitiesService.update(typeFacilities);

       if(typeFacilitiesResponse == null) {
           commonResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
           commonResponse.setData(null);
           commonResponse.setMessage("Data not found");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonResponse);
       }else{
           commonResponse.setStatusCode(HttpStatus.OK.value());
           commonResponse.setData(typeFacilitiesResponse);
           commonResponse.setMessage("Success update Type Facilities");
           return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
       }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteTypeFacilities(@PathVariable String id) {
        CommonResponse<String> commonResponse = new CommonResponse<>();
        typeFacilitiesService.delete(id);

        commonResponse.setStatusCode(HttpStatus.OK.value());
        commonResponse.setData(id);
        commonResponse.setMessage("Success delete Type Facilities");
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);

    }


}
