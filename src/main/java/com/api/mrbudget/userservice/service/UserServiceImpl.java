package com.dailytodo.user.service;

import com.dailytodo.user.dto.model.UserDto;
import com.dailytodo.user.dto.response.JwtResponse;
import com.dailytodo.user.exception.UserException;
import com.dailytodo.user.exception.EntityType;
import com.dailytodo.user.exception.ExceptionType;
import com.dailytodo.user.model.User;
import com.dailytodo.user.repository.UserRepository;
import com.dailytodo.user.security.JwtUtil;
import com.dailytodo.user.security.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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

            // Save new user
            userRepository.save(user);

            // Convert user to userDto
            UserDto newUserDto = modelMapper.map(user, UserDto.class);
//            return UserMapper.toUserDto(userRepository.save(user));
            return newUserDto;
        }

        throw exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, userDto.getEmail());
    }

    @Override
    public JwtResponse login(UserDto userDto) {

        // Control flows to Provider Manager, then, Authentication Provider
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(authentication);

        // Convert User object to UserDetails object
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtResponse(
                token,
                userPrincipal.getId(),
                userPrincipal.getEmail(),
                userPrincipal.getFirstName(),
                userPrincipal.getLastName()
        );
    }

    /**
     * Handles requests finding a user by email.
     *
     * @param email
     * @return UserDto
     */
    @Transactional
    public UserDto findByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));

        // Converts a user object to a userDto object
        if(user.isPresent()) return modelMapper.map(user.get(), UserDto.class);

        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return userException.throwException(entityType, exceptionType, args);
    }
}
