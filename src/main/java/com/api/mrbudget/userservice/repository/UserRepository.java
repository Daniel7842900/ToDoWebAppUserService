package com.api.mrbudget.userservice.repository;

import com.api.mrbudget.userservice.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
