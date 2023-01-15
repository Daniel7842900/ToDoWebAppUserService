package com.api.mrbudget.userservice.service;

import com.api.mrbudget.userservice.dto.model.UserDto;
import com.api.mrbudget.userservice.dto.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Author: Daniel Lim
 *
 * UserService interface
 */
public interface UserService  {


    /**
     * Register a user
     *
     * @param userDto
     * @return
     */
    UserDto signup(UserDto userDto);

    UserDto findByEmail(String email);

    JwtResponse login(UserDto userDto);

//    String
}
