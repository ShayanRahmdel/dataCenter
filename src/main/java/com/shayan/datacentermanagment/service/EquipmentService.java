package com.shayan.datacentermanagment.service;


import com.shayan.datacentermanagment.model.Equipment;

import java.util.Optional;

public interface EquipmentService {

    Equipment save(Equipment equipment);

    Equipment load(Long id);

    Equipment update(Equipment equipment);

    void delete(Long id);
}
