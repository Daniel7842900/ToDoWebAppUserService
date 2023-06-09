package com.dailytodo.userservice.dto.mapper;

import com.dailytodo.userservice.dto.model.UserDto;
import com.dailytodo.userservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
    }
}
