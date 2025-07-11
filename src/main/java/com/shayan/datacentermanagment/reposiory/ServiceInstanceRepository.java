package com.shayan.datacentermanagment.reposiory;


import com.shayan.datacentermanagment.model.ServiceInstance;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ServiceInstanceRepository extends JpaRepository<ServiceInstance,Long> {

}
