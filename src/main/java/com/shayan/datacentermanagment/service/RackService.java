package com.shayan.datacentermanagment.service;

import com.shayan.datacentermanagment.model.Rack;

import java.util.Optional;

public interface RackService {

    Rack save(Rack rack);

    Rack load(Long id);

    Rack update(Rack rack);

    void delete(Long id);
}
