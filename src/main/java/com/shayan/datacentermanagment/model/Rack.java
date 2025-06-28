package com.shayan.datacentermanagment.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class Rack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Integer rackIndex;


    private Integer unitCapacity=42;

    @ManyToOne
    @JoinColumn(name = "rack_row_id", nullable = false)
    private RackRow rackRow;

    @OneToMany(mappedBy = "rack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipmentList;
}
