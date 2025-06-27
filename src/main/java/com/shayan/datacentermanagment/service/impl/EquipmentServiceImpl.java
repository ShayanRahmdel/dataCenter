package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Equipment;
import com.shayan.datacentermanagment.reposiory.EquipmentRepository;
import com.shayan.datacentermanagment.service.EquipmentService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final ValidationUtil validationUtil;

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

    @Override
    public void delete(Long id) {
        validationUtil.validateExists(equipmentRepository, id, "Equipment");
        equipmentRepository.deleteById(id);
    }
}
