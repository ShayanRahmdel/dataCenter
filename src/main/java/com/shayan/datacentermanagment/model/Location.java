package com.shayan.datacentermanagment.model;

import com.shayan.datacentermanagment.model.enumration.LocationType;
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
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Location(String name, LocationType locationType,List<Location> parent,List<Location> children ) {
        this.children = children;
        this.locationType = locationType;
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Location parent;

    @OneToMany(mappedBy = "parent")
    private List<Location> children;
}
