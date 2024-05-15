package com.enigmacamp.reservationcampus.model.request;

import com.enigmacamp.reservationcampus.model.entity.constant.TypeFacilities;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ToolsRequest {
    private String toolsId;
    private String toolsName;
    private String toolsDescription;
    private String toolsPicture;
    private Integer toolsPrice;
    private Integer toolsStock;
    private TypeFacilities typeFacilities;
}
