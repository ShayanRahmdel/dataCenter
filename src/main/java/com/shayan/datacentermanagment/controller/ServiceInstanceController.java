package com.shayan.datacentermanagment.controller;

import com.shayan.datacentermanagment.dto.ServiceInstanceDto;

import com.shayan.datacentermanagment.model.Equipment;
import com.shayan.datacentermanagment.model.Invoice;
import com.shayan.datacentermanagment.model.ServiceInstance;
import com.shayan.datacentermanagment.service.EquipmentService;
import com.shayan.datacentermanagment.service.InvoiceService;
import com.shayan.datacentermanagment.service.ServiceInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceInstanceController {

    private final ServiceInstanceService serviceInstanceService;
    private final InvoiceService invoiceService;
    private final EquipmentService equipmentService;


    @PostMapping("/buyService")
    public ServiceInstanceDto buyService(@RequestBody ServiceInstanceDto dto) {
        ServiceInstance serviceInstance = dtoToEntity(dto);
        return entityToDto(serviceInstanceService.create(serviceInstance));

    }


    private ServiceInstance dtoToEntity(ServiceInstanceDto dto) {
        Invoice invoice = invoiceService.load(dto.getInvoiceId());
        Equipment equipment = equipmentService.load(dto.getEquipmentId());
        return ServiceInstance.builder().startDate(dto.getStartDate())
                .endDate(dto.getEndDate()).durationInMonths(dto.getDurationInMonths()).invoice(invoice).equipment(equipment).build();
    }


    private ServiceInstanceDto entityToDto(ServiceInstance entity) {
        return ServiceInstanceDto.builder().startDate(entity.getStartDate())
                .endDate(entity.getEndDate()).durationInMonths(entity.getDurationInMonths()).invoice(entity.getInvoice()).equipment(entity.getEquipment()).build();
    }


    private List<ServiceInstanceDto> EntityListToDto(List<ServiceInstance> serviceInstances) {
        return serviceInstances.stream().map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private List<ServiceInstance> dtoListToEntities(List<ServiceInstanceDto> dtoList) {
        return dtoList.stream().map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
