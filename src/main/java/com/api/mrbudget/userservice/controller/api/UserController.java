package com.api.mrbudget.userservice.controller.api;

import com.api.mrbudget.userservice.controller.request.LoginRequest;
import com.api.mrbudget.userservice.controller.request.SignupRequest;
import com.api.mrbudget.userservice.dto.model.UserDto;
import com.api.mrbudget.userservice.dto.response.JwtResponse;
import com.api.mrbudget.userservice.dto.response.Response;
import com.api.mrbudget.userservice.model.User;
import com.api.mrbudget.userservice.security.JwtUtil;
import com.api.mrbudget.userservice.security.UserDetailsImpl;
import com.api.mrbudget.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Daniel Lim
 *
 * Controller for user (and auth).
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/login")
    public String login() {
        return "hello world";
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequest signupRequest) {
//        System.out.println(signupRequest.getFirstName());
//        System.out.println(signupRequest.getLastName());
//        System.out.println(signupRequest.getEmail());
//        System.out.println(signupRequest.getPassword());

        // Create a UserDto
        UserDto userDto = new UserDto()
                .setFirstName(signupRequest.getFirstName())
                .setLastName(signupRequest.getLastName())
                .setEmail(signupRequest.getEmail())
                .setPassword(signupRequest.getPassword());

        return userService.signup(userDto);
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest loginRequest) {
//        System.out.println(loginRequest.getEmail());
//        System.out.println(loginRequest.getPassword());
//        System.out.println("passing printing email and password");

        // Control flows to Provider Manager, then, Authentication Provider
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        System.out.println(authentication);
//        System.out.println("passing authentication");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        // Convert User object to UserDetails object
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

//        System.out.println(jwt);
//        System.out.println("passing jwt");
        return Response.ok().setPayload(
                new JwtResponse(
                        jwt,
                        userPrincipal.getId(),
                        userPrincipal.getEmail(),
                        userPrincipal.getFirstName(),
                        userPrincipal.getLastName()
                )
        );
    }


}
