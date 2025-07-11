package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.aspect.LogActivity;
import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Equipment;
import com.shayan.datacentermanagment.model.enumration.EquipmentType;
import com.shayan.datacentermanagment.reposiory.EquipmentRepository;
import com.shayan.datacentermanagment.service.EquipmentService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final ValidationUtil validationUtil;

    @LogActivity("adding new Equipment")
    @Override
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment load(Long id) {
        return equipmentRepository.findById(id).orElseThrow(()->new NotFoundException("not found Equipment"));
    }

    @Override
    public Equipment update(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @LogActivity("delete Equipment")
    @Override
    public void delete(Long id) {
        validationUtil.validateExists(equipmentRepository, id, "Equipment");
        equipmentRepository.deleteById(id);
    }

    @Override
    public Page<Equipment> findAllServer(Pageable pageable) {

        return equipmentRepository.loadServerByType(EquipmentType.SERVER,pageable);
    }

}
