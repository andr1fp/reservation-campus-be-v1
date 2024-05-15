package com.enigmacamp.reservationcampus.controller.facilities;

import com.enigmacamp.reservationcampus.model.facilities.Tools;
import com.enigmacamp.reservationcampus.model.response.PageResponseWrapper;
import com.enigmacamp.reservationcampus.services.facilities.ToolService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(APIPath.BASE_PATH + "/tools")
public class ToolsController {
    ToolService toolService;

    @Autowired
    public ToolsController(ToolService toolService){
        this.toolService = toolService;
    }

    @PostMapping
    public Tools saveTools(@RequestBody Tools tools){
        return toolService.saveTools(tools);
    }

    @GetMapping("/all")
    public List<Tools> getAllTools(){
        return toolService.getAllTools();
    }

    @GetMapping
    public PageResponseWrapper<Tools> getAllTools(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                                                  @RequestParam(name = "size", defaultValue = "5") Integer pageSize
                                                  ){
        Tools tools = new Tools();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Tools> page = toolService.getToolsPerPage(pageable, tools);
        return new PageResponseWrapper<>(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTools(@PathVariable String id){
        toolService.deleteToolsById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tools with ID " + id + " has been deleted");
    }

    @GetMapping("/{id}")
    public Tools getToolsById(@PathVariable String id){
        return toolService.getToolsById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Tools> updateTools(@PathVariable String id, @RequestBody Tools tools){
        for (Tools t : toolService.getAllTools()){
            if(t.getId().equals(id)){
                t.setName(tools.getName());
                t.setPrice(tools.getPrice());
                t.setStock(tools.getStock());
                t.setDescription(tools.getDescription());
                return ResponseEntity.ok(toolService.saveTools(t));
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam(value = "picture") MultipartFile picture) {
//        try {
//            String fileName = imageStorageService.storeFile(picture);
//            return "File uploaded successfully: " + fileName;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Failed to upload file";
//        }
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Tools> uploadTools(Tools tools, @RequestParam("file") MultipartFile picture) {
//        Tools savedTools = toolService.uploadTools(tools, picture);
//        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTools.getId()).toUri()).body(savedTools);
//    }

    @PostMapping("/add")
    public void uploadTools(@RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam("price") Integer price,
                            @RequestParam("stock") Integer stock,
                            @RequestParam("picture") MultipartFile picture) throws IOException {

        Tools tools = new Tools();
        tools.setName(name);
        tools.setDescription(description);
        tools.setPrice(price);
        tools.setStock(stock);
        tools.setPicture(picture.getBytes());
        toolService.uploadTools(tools);
    }
}