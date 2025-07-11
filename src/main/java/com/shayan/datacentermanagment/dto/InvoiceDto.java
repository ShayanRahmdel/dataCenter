package com.shayan.datacentermanagment.dto;

import com.shayan.datacentermanagment.model.ServiceInstance;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceDto {

    private BigDecimal amount;
    private LocalDate issueDate;
    private boolean paid;
    private Long serviceInstanceId;
    private ServiceInstance serviceInstance;
}
