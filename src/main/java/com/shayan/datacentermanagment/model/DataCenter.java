package com.shayan.datacentermanagment.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "data_center")
@Builder
@EqualsAndHashCode
public class DataCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "dataCenter")
    private List<RackRow> rackRows;

    @OneToOne
    @JoinColumn(name = "location_id",nullable = false)
    private Location location;

}
