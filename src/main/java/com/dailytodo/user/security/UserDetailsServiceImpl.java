package com.dailytodo.user.security;


import com.dailytodo.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import com.dailytodo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Daniel Lim
 *
 * Custom UserDetailsService implementation for Spring Security
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null) throw new UsernameNotFoundException("User not found with the email: " + email);

        Set<GrantedAuthority> authorities = new HashSet<>();
        System.out.println("calling loadUserByUsername...");
        System.out.println("user email is: ");
        System.out.println(user.getEmail());

//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        return UserDetailsImpl.build(user);
    }
}
