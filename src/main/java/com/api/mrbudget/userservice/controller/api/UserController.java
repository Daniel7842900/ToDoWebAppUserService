package com.dailytodo.user.controller.api;

import com.dailytodo.user.controller.request.LoginRequest;
import com.dailytodo.user.controller.request.SignupRequest;
import com.dailytodo.user.dto.model.UserDto;
import com.dailytodo.user.dto.response.JwtResponse;
import com.dailytodo.user.dto.response.Response;
import com.dailytodo.user.security.JwtUtil;
import com.dailytodo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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

        UserDto userDto = new UserDto()
                .setEmail(loginRequest.getEmail())
                .setPassword(loginRequest.getPassword());

        JwtResponse jwtResponse = userService.login(userDto);

//        // Control flows to Provider Manager, then, Authentication Provider
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtil.generateToken(authentication);
//
//        // Convert User object to UserDetails object
//        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

//        return Response.ok().setPayload(
//                new JwtResponse(
//                        jwt,
//                        userPrincipal.getId(),
//                        userPrincipal.getEmail(),
//                        userPrincipal.getFirstName(),
//                        userPrincipal.getLastName()
//                )
//        );

        return Response.ok().setPayload(jwtResponse);
    }


}
