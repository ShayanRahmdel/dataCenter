package com.shayan.datacentermanagment.reposiory;

import com.shayan.datacentermanagment.model.DataCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCenterRepository extends JpaRepository<DataCenter,Long> {
}
