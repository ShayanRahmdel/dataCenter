package com.shayan.datacentermanagment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shayan.datacentermanagment.model.Rack;
import com.shayan.datacentermanagment.model.enumration.EquipmentType;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Builder
public class EquipmentDto {

    private String name;
    private Integer startUnit;
    private Integer unitSize;
    private BigDecimal price;
    private EquipmentType equipmentType;
    @JsonIgnore
    private Long rackId;
    @JsonIgnore
    private Rack rack;
}
