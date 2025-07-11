package com.shayan.datacentermanagment.dto;


import com.shayan.datacentermanagment.model.Ticket;
import com.shayan.datacentermanagment.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class TicketReplyDto {

    private String replyMessage;
    private LocalDateTime replyTime;
    private Long ticketId;
    private Long responderId;
    private Ticket ticket;
    private User responder;
}
