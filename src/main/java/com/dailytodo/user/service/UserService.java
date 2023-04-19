package com.dailytodo.user.service;

import com.dailytodo.user.dto.model.UserDto;
import com.dailytodo.user.dto.response.JwtResponse;

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
