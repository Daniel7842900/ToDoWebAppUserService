package com.dailytodo.userservice.repository;

import com.dailytodo.userservice.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
