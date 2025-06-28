package com.shayan.datacentermanagment.service;

import com.shayan.datacentermanagment.model.Location;



public interface LocationService {

    Location save(Location location);

    Location load(Long id);

    Location update(Location location);

    void delete(Long id);
}
