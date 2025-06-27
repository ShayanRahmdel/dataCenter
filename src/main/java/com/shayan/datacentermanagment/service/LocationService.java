package com.shayan.datacentermanagment.service;

import com.shayan.datacentermanagment.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    Location save(Location location);

    Location load(Long id);

    Location update(Location location);

    void delete(Long id);

    List<Location> createLocation();
}
