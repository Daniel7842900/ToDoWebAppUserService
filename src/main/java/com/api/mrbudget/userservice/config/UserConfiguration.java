package com.api.mrbudget.userservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Daniel Lim
 *
 * Configuration for the whole User Service
 */
@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories
//@EnableJpaAuditing
public class UserMapperConfig {

    /**
     * Configures the mapper (User -> UserDto)
     *
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }
}
