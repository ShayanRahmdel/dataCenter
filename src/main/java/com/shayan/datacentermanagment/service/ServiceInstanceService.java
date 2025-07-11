package com.shayan.datacentermanagment.service;


import com.shayan.datacentermanagment.model.ServiceInstance;

public interface ServiceInstanceService {

    ServiceInstance create(ServiceInstance serviceInstance/*,Long equipmentId*/);


    ServiceInstance load(Long id);


    ServiceInstance update(Long id,ServiceInstance serviceInstance);


    void delete(Long id);

    ServiceInstance renew(Long serviceInstanceId, int additionalMonths);

}
