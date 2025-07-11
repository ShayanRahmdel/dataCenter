package com.shayan.datacentermanagment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TicketReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String replyMessage;
    private LocalDateTime replyTime=LocalDateTime.now();

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private User responder;
}
