package com.dailytodo.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/**
 * Configuration class for disabling Spring Security.
 * This is the purpose of the development.
 */
//@Configuration
//public class ApplicationNoSecurity {
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .antMatchers("/**");
//    }
//}
