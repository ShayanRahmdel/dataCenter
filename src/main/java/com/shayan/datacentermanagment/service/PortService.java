package com.shayan.datacentermanagment.service;

import com.shayan.datacentermanagment.model.Location;
import com.shayan.datacentermanagment.model.Port;

import java.util.Optional;

public interface PortService {

    Port save(Port port);

    Port load(Long id);

    Port update(Port port);

    void delete(Long id);
}
