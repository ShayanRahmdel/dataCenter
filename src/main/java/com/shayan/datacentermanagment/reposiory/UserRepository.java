package com.shayan.datacentermanagment.reposiory;


import com.shayan.datacentermanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
