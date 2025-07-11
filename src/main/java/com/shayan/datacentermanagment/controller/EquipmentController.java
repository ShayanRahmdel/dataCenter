package com.shayan.datacentermanagment.controller;

import com.shayan.datacentermanagment.dto.EquipmentDto;
import com.shayan.datacentermanagment.model.Equipment;
import com.shayan.datacentermanagment.model.Rack;
import com.shayan.datacentermanagment.model.enumration.EquipmentType;
import com.shayan.datacentermanagment.service.EquipmentService;
import com.shayan.datacentermanagment.service.RackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final RackService rackService;


    @GetMapping
    public Page<EquipmentDto> seeAllEquipment(@PageableDefault(size = 10, sort = "name",
            direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<Equipment> allServer = equipmentService.findAllServer(pageable);
        List<Equipment> equipmentList = allServer.getContent();
        return new PageImpl<>(entitiesToDtoList(equipmentList));

    }

  private EquipmentDto entityToDto(Equipment equipment){
      Rack rack = rackService.load(equipment.getRack().getId());
      return EquipmentDto.builder().price(equipment.getPrice()).equipmentType(equipment.getEquipmentType())
                .startUnit(equipment.getStartUnit()).unitSize(equipment.getUnitSize()).rack(rack).build();

  }

  private List<EquipmentDto> entitiesToDtoList(List<Equipment> equipments){
        return equipments.stream().map(this::entityToDto)
                .collect(Collectors.toList());
  }

}
