package com.copious.training.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            return new User("admin",
                    "$2y$12$vsqTdSd6riwSk5YPdxa1bOr57hW4Ilznq0.QHlRN/r7AZM1Y1hj66",
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }
        if (username.equals("mahesh")) {
            return new User("mahesh",
                    "$2y$12$tW8sqhdq9oWPuHZATqfvQe7IOXDTrLjwCNdeCaekeJhW0Gto3bIx6",
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
            );
        }
        throw new UsernameNotFoundException("User not found with the name " + username);
    }

}

