package com.shayan.datacentermanagment.reposiory;

import com.shayan.datacentermanagment.model.DataCenter;
import com.shayan.datacentermanagment.model.RackRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RackRowRepository extends JpaRepository<RackRow,Long> {
}
