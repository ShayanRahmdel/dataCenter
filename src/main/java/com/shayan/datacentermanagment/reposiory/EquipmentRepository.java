package com.shayan.datacentermanagment.reposiory;

import com.shayan.datacentermanagment.model.Equipment;
import com.shayan.datacentermanagment.model.enumration.EquipmentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquipmentRepository extends JpaRepository<Equipment,Long> {

    @Query("SELECT e FROM Equipment e WHERE e.equipmentType = :type")
    Page<Equipment> loadServerByType(@Param("type")EquipmentType type, Pageable pageable);

}
