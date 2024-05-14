package com.enigmacamp.reservationcampus.controller;

import com.enigmacamp.reservationcampus.model.facilities.Tools;
import com.enigmacamp.reservationcampus.model.response.PageResponseWrapper;
import com.enigmacamp.reservationcampus.service.ToolService;
import com.enigmacamp.reservationcampus.utils.constant.APIPath;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
