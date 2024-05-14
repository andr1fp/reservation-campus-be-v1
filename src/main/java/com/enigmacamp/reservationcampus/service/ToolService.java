package com.enigmacamp.reservationcampus.service;

import com.enigmacamp.reservationcampus.model.facilities.Tools;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ToolService {
    Tools saveTools(Tools tools);
    Tools getToolsById(String id);
    List<Tools> getAllTools();
    Tools updateTools(String id,Tools tools);
    void deleteToolsById(String id);

    Page<Tools> getToolsPerPage(Pageable pageable, Tools tools);
}
