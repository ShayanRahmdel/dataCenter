package com.shayan.datacentermanagment.service;

import com.shayan.datacentermanagment.model.DataCenter;

import java.util.Optional;

public interface DataCenterService {

    DataCenter save(DataCenter dataCenter);

    DataCenter load(Long id);

    DataCenter update(DataCenter dataCenter);

    void delete(Long id);

}
