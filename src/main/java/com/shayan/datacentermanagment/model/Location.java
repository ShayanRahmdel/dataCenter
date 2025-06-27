package com.shayan.datacentermanagment.model;

import com.shayan.datacentermanagment.model.enumration.LocationType;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Location parent;

    @OneToMany(mappedBy = "parent")
    private List<Location> children;
}
