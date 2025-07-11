package com.shayan.datacentermanagment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "service_instance")
@Builder
public class ServiceInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer durationInMonths;

    @ManyToOne
    private User user;

    @ManyToOne
    private Equipment equipment; // فقط نوع SERVER

    @OneToOne(cascade = CascadeType.ALL)
    private Invoice invoice;
}
