package com.example.task.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Repository
public class UserDao {
    private final static List<UserDetails> APP_USERS = List.of(
            new User("test@gmail.com",
                    "qwerty",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("test2@gmail.com",
                    "qwerty",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
    );


    public UserDetails findUserByEmail(String email){
        return APP_USERS
                .stream()
                .filter(u->u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() ->new UsernameNotFoundException("No user found for given credentials"));
    }
}
