package com.shayan.datacentermanagment.reposiory;


import com.shayan.datacentermanagment.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ActivityLogRepo extends JpaRepository<ActivityLog,Long> {



}
