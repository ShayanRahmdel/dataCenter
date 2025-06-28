package com.shayan.datacentermanagment.reposiory;

import com.shayan.datacentermanagment.model.DataCenter;
import com.shayan.datacentermanagment.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataCenterRepository extends JpaRepository<DataCenter,Long> {


    @Query("select count(dc) > 0 from DataCenter dc where dc.location = :location")
    boolean existsByLocation(@Param("location") Location location);
}
