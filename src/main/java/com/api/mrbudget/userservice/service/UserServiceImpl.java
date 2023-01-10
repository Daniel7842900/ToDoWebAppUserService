package com.api.mrbudget.userservice.service;

import com.api.mrbudget.userservice.dto.mapper.UserMapper;
import com.api.mrbudget.userservice.dto.model.UserDto;
import com.api.mrbudget.userservice.exception.UserException;
import  com.api.mrbudget.userservice.exception.EntityType;
import  com.api.mrbudget.userservice.exception.ExceptionType;
import com.api.mrbudget.userservice.model.User;
import com.api.mrbudget.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.api.mrbudget.userservice.exception.EntityType.USER;
import static com.api.mrbudget.userservice.exception.ExceptionType.DUPLICATE_ENTITY;


/**
 * Author: Daniel Lim
 *
 * Implementation class for UserService interface
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserException userException;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Handles the signup request.
     * Checks whether the user already exists.
     * If it exists, throw a duplicate error, otherwise, create a new user.
     *
     * @param userDto
     * @return UserDto
     */
    @Override
    public UserDto signup(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        if(user == null) {
            user = new User()
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setEmail(userDto.getEmail())
                    .setPassword(passwordEncoder.encode(userDto.getPassword()));

            return UserMapper.toUserDto(userRepository.save(user));
        }

        throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());

    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return userException.throwException(entityType, exceptionType, args);
    }
}
