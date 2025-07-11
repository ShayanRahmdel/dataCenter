package com.shayan.datacentermanagment.dto;

import com.shayan.datacentermanagment.model.Equipment;
import com.shayan.datacentermanagment.model.Invoice;
import com.shayan.datacentermanagment.model.User;
import com.shayan.datacentermanagment.model.enumration.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ServiceInstanceDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer durationInMonths;
    private Long userId;
    private Long equipmentId;
    private Equipment equipment;
    private Long invoiceId;
    private Invoice invoice;

}
