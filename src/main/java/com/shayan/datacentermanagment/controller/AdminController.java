package com.shayan.datacentermanagment.controller;

import com.shayan.datacentermanagment.model.ActivityLog;
import com.shayan.datacentermanagment.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ActivityLogService activityLogService;

    @RequestMapping("getAllActivityLog")
    public Page<ActivityLog> getAllActivityLog(@PageableDefault(size = 10, sort = "timestamp",
            direction = Sort.Direction.DESC) Pageable pageable){
        return activityLogService.getAll(pageable);
    }


}
