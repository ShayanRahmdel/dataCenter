package com.shayan.datacentermanagment.reposiory;

import com.shayan.datacentermanagment.model.DataCenter;
import com.shayan.datacentermanagment.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port,Long> {
}
