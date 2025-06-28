package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.Location;
import com.shayan.datacentermanagment.model.enumration.LocationType;
import com.shayan.datacentermanagment.reposiory.LocationRepository;
import com.shayan.datacentermanagment.service.LocationService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final ValidationUtil validationUtil;


    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location load(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new NotFoundException("not found Location"));
    }

    @Override
    public Location update(Location location) {
        return null;
    }

    @Override
    public void delete(Long id) {
        validationUtil.validateExists(locationRepository, id, "Location");
        locationRepository.deleteById(id);
    }



}
