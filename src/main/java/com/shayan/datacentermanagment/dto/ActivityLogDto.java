package com.shayan.datacentermanagment.dto;

import com.shayan.datacentermanagment.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityLogDto {

    private Long id;
    private String action;
    private LocalDateTime timestamp;
    private User user;
}
