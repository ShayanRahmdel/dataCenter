package com.shayan.datacentermanagment.dto;

import com.shayan.datacentermanagment.model.TicketReply;
import com.shayan.datacentermanagment.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TicketDto {

    private String subject;
    private String message;
    private LocalDateTime createdAt;
    private Long customerId;
    private User customer;
    private List<TicketReply> replies;
}
