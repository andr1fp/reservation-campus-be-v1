package com.enigmacamp.reservationcampus.model.response;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ToolsDTO {
    private String toolsId;
    private String toolsName;
    private String toolsDescription;
    private Integer toolsPrice;
    private Integer toolsStock;
    private TypeFacilities typeFacilities;
}
