package com.shayan.datacentermanagment.model;

import com.shayan.datacentermanagment.model.enumration.EquipmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "start_unit")
    private Integer startUnit;

    @Min(1)
    @Max(4)
    @Column(name = "unit_size")
    private Integer unitSize;

    @AssertTrue(message = "unitSize must be 1 , 2 or  4")
    public Boolean isValidUnitSize(){
        return unitSize == 1 || unitSize == 2 || unitSize == 4;
    }

    @AssertTrue(message = "Invalid rack unit range")
    public Boolean isRackSpaceValid() {
        if (startUnit == null || unitSize == null || rack == null) return true;
        return startUnit >= 1 && (startUnit + unitSize - 1) <= rack.getUnitCapacity();
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "equipment_type")
    private EquipmentType equipmentType;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "equipment")
    private List<Port> ports;

    @ManyToOne
    private Rack rack;
}
