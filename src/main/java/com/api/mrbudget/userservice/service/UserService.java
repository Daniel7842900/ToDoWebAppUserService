package com.api.mrbudget.userservice.service;

import com.api.mrbudget.userservice.dto.model.mapper.UserDto;

/**
 * Author: Daniel Lim
 *
 * UserService interface
 */
public interface UserService {

    /**
     * Register a user
     *
     * @param userDto
     * @return
     */
    UserDto signup(UserDto userDto);
}
