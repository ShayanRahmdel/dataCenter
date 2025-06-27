package com.shayan.datacentermanagment.reposiory;

import com.shayan.datacentermanagment.model.DataCenter;
import com.shayan.datacentermanagment.model.Rack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RackRepository extends JpaRepository<Rack,Long> {
}
