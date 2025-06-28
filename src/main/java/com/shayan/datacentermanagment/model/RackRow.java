package com.shayan.datacentermanagment.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "rack_row")
@Builder
@EqualsAndHashCode
public class RackRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer rowIndex;


    @OneToMany(mappedBy = "rackRow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rack> racks;


    @ManyToOne
    @JoinColumn(name = "data_center_id", nullable = false)
    private DataCenter dataCenter;

}
