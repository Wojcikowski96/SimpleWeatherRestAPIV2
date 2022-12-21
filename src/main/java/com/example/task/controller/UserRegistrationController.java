package com.example.task.controller;

import com.example.task.clients.model.RegistrationRequest;
import com.example.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRegistrationController {
@Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationRequest request){
        ResponseEntity response;
        response = userService.registerUser(request);
        return response;
        // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));;
        //To sprawdzenie powinno być w serwisie


    };
}
