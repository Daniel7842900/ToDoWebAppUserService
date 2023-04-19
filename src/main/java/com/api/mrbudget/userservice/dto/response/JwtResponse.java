package com.dailytodo.user.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtResponse {
    private String token;
    private Long id;
    private String tokenPrefix = "Bearer";
    private String email;
    private String firstName;
    private String lastName;

    public JwtResponse(String token, Long id, String email, String firstName, String lastName) {
        this.token = token;
        this.id = id;
//        this.tokenPrefix = tokenPrefix;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
