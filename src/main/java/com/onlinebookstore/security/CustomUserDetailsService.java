package com.onlinebookstore.security;

import com.onlinebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // For demo, static user. In real app, fetch from DB
        return userRepository.findByUserName(username)
                .map(user->new User(user.getUserName(),user.getPassword(),List.of(()->user.getRole().getRoleName())))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

}
