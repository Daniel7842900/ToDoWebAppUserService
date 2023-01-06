package com.api.mrbudget.userservice.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: Daniel Lim
 *
 * Class for a signup request.
 * First name, last name, email and password are needed for the signup.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
