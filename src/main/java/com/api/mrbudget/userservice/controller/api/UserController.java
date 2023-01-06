package com.api.mrbudget.userservice.controller.api;

import com.api.mrbudget.userservice.controller.request.LoginRequest;
import com.api.mrbudget.userservice.controller.request.SignupRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Daniel Lim
 *
 * Controller for user.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest) {
        return "";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return "";
    }


}
