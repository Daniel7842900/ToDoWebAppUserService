package com.api.mrbudget.userservice.controller.api;

import com.api.mrbudget.userservice.controller.request.LoginRequest;
import com.api.mrbudget.userservice.controller.request.SignupRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Daniel Lim
 *
 * Controller for user.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "hello world";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest) {
        System.out.println(signupRequest.getFirstName());
        System.out.println(signupRequest.getLastName());
        System.out.println(signupRequest.getEmail());
        System.out.println(signupRequest.getPassword());

        return "";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return "";
    }


}
