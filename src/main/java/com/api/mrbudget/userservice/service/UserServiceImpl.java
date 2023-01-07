package com.api.mrbudget.userservice.service;

import com.api.mrbudget.userservice.dto.model.mapper.UserDto;

/**
 * Author: Daniel Lim
 *
 * Implementation class for UserService interface
 */
public class UserServiceImpl implements UserService {

    /**
     * Signup implementation
     *
     * @param userDto
     * @return
     */
    @Override
    public UserDto signup(UserDto userDto) {
        return new UserDto();
    }
}
