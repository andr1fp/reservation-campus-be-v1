package com.enigmacamp.reservationcampus.service.impl;

import com.enigmacamp.reservationcampus.model.facilities.Tools;
import com.enigmacamp.reservationcampus.repository.ToolsRepository;
import com.enigmacamp.reservationcampus.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolsServiceImpl implements ToolService {

    ToolsRepository toolsRepository;

    @Autowired
    public ToolsServiceImpl(ToolsRepository toolsRepository){
        this.toolsRepository = toolsRepository;
    }

    @Override
    public Tools saveTools(Tools tools) {
        return toolsRepository.save(tools);
    }

    @Override
    public Tools getToolsById(String id) {
        return toolsRepository.findById(id).get();
    }

    @Override
    public List<Tools> getAllTools() {
        return toolsRepository.findAll();
    }

    @Override
    public Tools updateTools(String id, Tools tools) {
        for(Tools t : toolsRepository.findAll()){
            if(t.getId().equals(id)){
                t.setName(tools.getName());
                t.setPrice(tools.getPrice());
                t.setStock(tools.getStock());
                t.setDescription(tools.getDescription());
                return t;
            }
        }
        return null;
    }

//    @Override
//    public Tools updateTools(String id,Tools tools) {
//        if (toolsRepository.existsById(id)) {
//            tools.setId(id);
//            return toolsRepository.save(tools);
////        if(toolsRepository.findById(tools.getId()).isPresent()){
////            Tools tools1 = new Tools();
////            tools1.setName(tools.getName());
////            tools1.setPrice(tools.getPrice());
////            tools1.setStock(tools.getStock());
////            tools1.setDescription(tools.getDescription());
////            toolsRepository.save(tools1);
////            return tools;
//        }else {
//            throw new RuntimeException("Tools not found");
//        }
//    }

    @Override
    public void deleteToolsById(String id) {
        toolsRepository.deleteById(id);
    }
}
