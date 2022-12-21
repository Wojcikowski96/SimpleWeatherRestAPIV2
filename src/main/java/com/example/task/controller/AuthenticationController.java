package com.example.task.controller;

import com.example.task.clients.model.AuthenticationRequest;
import com.example.task.configuration.utils.JwtUtils;
//import com.example.task.repository.UserDao;
import com.example.task.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService myUserDetailsService;

    private final JwtUtils jwtUtils;
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails user = myUserDetailsService.loadUserByUsername(request.getEmail());
        if(user !=null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
    return ResponseEntity.status(400).body("Some error happened");
    };
}
