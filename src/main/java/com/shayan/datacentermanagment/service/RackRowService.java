package com.shayan.datacentermanagment.service;

import com.shayan.datacentermanagment.model.Rack;
import com.shayan.datacentermanagment.model.RackRow;

import java.util.Optional;

public interface RackRowService {

    RackRow save(RackRow rackRow);

    RackRow load(Long id);

    RackRow update(RackRow rackRow);

    void delete(Long id);
}
