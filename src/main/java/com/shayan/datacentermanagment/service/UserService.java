package com.shayan.datacentermanagment.service;


import com.shayan.datacentermanagment.model.User;

public interface UserService {

    User create(User user);

    User load(Long id);

    void delete(Long id);

    User findByUsername(String username);


}
