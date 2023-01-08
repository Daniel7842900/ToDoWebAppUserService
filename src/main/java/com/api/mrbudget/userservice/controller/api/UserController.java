package com.api.mrbudget.userservice.controller.api;

import com.api.mrbudget.userservice.controller.request.LoginRequest;
import com.api.mrbudget.userservice.controller.request.SignupRequest;
import com.api.mrbudget.userservice.dto.model.UserDto;
import com.api.mrbudget.userservice.model.User;
import com.api.mrbudget.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Daniel Lim
 *
 * Controller for user.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "hello world";
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequest signupRequest) {
        System.out.println(signupRequest.getFirstName());
        System.out.println(signupRequest.getLastName());
        System.out.println(signupRequest.getEmail());
        System.out.println(signupRequest.getPassword());
        // Create a UserDto

        UserDto userDto = new UserDto()
                .setFirstName(signupRequest.getFirstName())
                .setLastName(signupRequest.getLastName())
                .setEmail(signupRequest.getEmail())
                .setPassword(signupRequest.getPassword());

        return userService.signup(userDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return "";
    }


}
