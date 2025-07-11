package com.shayan.datacentermanagment.service;



import com.shayan.datacentermanagment.model.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityLogService {


    Page<ActivityLog> getAll(Pageable pageable);

}
