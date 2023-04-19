package com.api.mrbudget.userservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.api.mrbudget.userservice.security.UserDetailsImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Daniel Lim
 *
 * Utility class for JWT.
 *
 */
@Component
public class JwtUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Generates a jwt token with the provided authentication object.
     *
     * @param authentication
     * @return String
     */
    public String generateToken(Authentication authentication) {
        System.out.println("calling generateToken...");
        Map<String, Object> claims = new HashMap<>();
        System.out.println("principal is?");
        System.out.println(authentication.getPrincipal());

        // Convert User object to UserDetailsImpl object
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("user principal email?");
        System.out.println(userPrincipal.getEmail());

        return Jwts
                .builder()
                .setSubject(userPrincipal.getEmail())
//                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Retrieves the user email from the provided token.
     *
     * @param token
     * @return
     */
    public String getUserEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
