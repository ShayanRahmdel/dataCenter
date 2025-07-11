package com.shayan.datacentermanagment.controller;

import com.shayan.datacentermanagment.dto.UserDto;
import com.shayan.datacentermanagment.model.CustomUserDetails;
import com.shayan.datacentermanagment.model.User;
import com.shayan.datacentermanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;



    @PostMapping("/signup")
    public UserDto createCustomer(@RequestBody UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = convertDtoToEntity(userDto);
        userService.create(user);
        return convertEntityToDto(user);
    }


    @PutMapping("/changePassword")
    public UserDto changePassword(@RequestBody UserDto dto){
        User user = userService.findByUsername(dto.getUsername());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User byUsername = userService.findByUsername(username);
        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("password is duplicated");
        }
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.create(user);
        return convertEntityToDto(user);
    }




    private User convertDtoToEntity(UserDto userDto){
        return User.builder().username(userDto.getUsername())
                .password(userDto.getPassword()).role(userDto.getRole()).build();
    }

    private UserDto convertEntityToDto(User user){
        return UserDto.builder().username(user.getUsername())
                .password(user.getPassword()).role(user.getRole()).build();
    }

    private List<User> convertListDtoToEntity(List<UserDto> dtoList) {
        return dtoList.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    private List<UserDto> convertListEntityToDtoList(List<User> users) {
        return users.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

}
