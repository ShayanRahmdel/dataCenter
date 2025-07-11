package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.aspect.LogActivity;
import com.shayan.datacentermanagment.exception.NotFoundException;
import com.shayan.datacentermanagment.model.User;
import com.shayan.datacentermanagment.reposiory.UserRepository;
import com.shayan.datacentermanagment.service.UserService;
import com.shayan.datacentermanagment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;

    @LogActivity("create user")
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User load(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("not Found User"));
    }

    @LogActivity("delete user")
    @Override
    public void delete(Long id) {
            validationUtil.validateExists(userRepository,id,"User");
            userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
