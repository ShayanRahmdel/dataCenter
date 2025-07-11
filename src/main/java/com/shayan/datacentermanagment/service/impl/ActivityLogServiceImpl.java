package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.model.ActivityLog;
import com.shayan.datacentermanagment.reposiory.ActivityLogRepo;
import com.shayan.datacentermanagment.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepo activityLogRepo;

    @Override
    public Page<ActivityLog> getAll(Pageable pageable) {
        return activityLogRepo.findAll(pageable);
    }
}
