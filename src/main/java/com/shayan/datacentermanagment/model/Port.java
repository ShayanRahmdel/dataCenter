package com.shayan.datacentermanagment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "port_number")
    private Integer portNumber;

    @ManyToOne
    private Equipment equipment;

    @OneToOne
    @JoinColumn(name = "connected_to_port_id")
    private Port connectedTo;

    @ManyToOne
    @JoinColumn(name = "via_patch_panel_port_id")
    private Port viaPatchPanelPort;
}
