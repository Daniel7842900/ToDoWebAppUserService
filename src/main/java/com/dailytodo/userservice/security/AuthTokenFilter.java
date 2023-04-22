package com.dailytodo.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Daniel Lim
 *
 * Filter for auth.
 * Filter gets triggered right after web security configuration is complete.
 *
 */
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Retrieve a jwt token
            String token = parseJwt(request);

            System.out.println("token is?");
            System.out.println(token);

            // TODO this needs to be tested. Haven't been able to get the existing token.
            if(token != null) {
                String email = jwtUtil.getUserEmailFromJwtToken(token);
                System.out.println("making here? after email");
                System.out.println("email is: ");
                System.out.println(email);

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                System.out.println("making here? after userDetails");
                System.out.println("userDetails is: ");
                System.out.println(userDetails);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                System.out.println("making here? after authentication");
                System.out.println("authentication is: ");
                System.out.println(authentication);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                System.out.println("making here? after set details");

                SecurityContextHolder.getContext().setAuthentication(authentication);


                System.out.println("making here?");

                // Add a header - Authorization with prefix + token
//                response.addHeader("Authorization", "Bearer " + token);

            }
        } catch (Exception e) {
            System.out.println("catching exception from doFilterInternal");
        }

        // Control flow moves on to Authentication Manager, which is in UserController.
        filterChain.doFilter(request, response);
    }

    /**
     * Retrieves a JWT token from the request header.
     * Gets rid of prefix and returns only the jwt.
     * Returns null if a JWT token can't be found in header.
     *
     * @param request
     * @return String
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        System.out.println("headerAuth");
        System.out.println(headerAuth);

        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            System.out.println("taking out bearer");
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
