package com.shayan.datacentermanagment.reposiory;

import com.shayan.datacentermanagment.model.DataCenter;
import com.shayan.datacentermanagment.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query("select l from Location l where l.name = 'ایران' ")
    Location findIranLocation();
}
