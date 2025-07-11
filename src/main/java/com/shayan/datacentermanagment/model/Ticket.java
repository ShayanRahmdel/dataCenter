package com.shayan.datacentermanagment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String message;
    private LocalDateTime createdAt;

    @ManyToOne
    private User customer;

    @OneToMany(mappedBy = "ticket")
    private List<TicketReply> replies;
}
