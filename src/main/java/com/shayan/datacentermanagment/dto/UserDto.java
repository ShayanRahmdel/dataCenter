package com.shayan.datacentermanagment.dto;

import com.shayan.datacentermanagment.model.enumration.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Long serviceInstanceId;
}
