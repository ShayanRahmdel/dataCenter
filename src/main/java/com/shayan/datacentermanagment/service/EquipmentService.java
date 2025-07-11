package com.shayan.datacentermanagment.service;


import com.shayan.datacentermanagment.model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EquipmentService {

    Equipment save(Equipment equipment);

    Equipment load(Long id);

    Equipment update(Equipment equipment);

    void delete(Long id);

    Page<Equipment> findAllServer(Pageable pageable);

}
