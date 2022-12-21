package com.example.task.service;

import com.example.task.clients.model.RegistrationRequest;
import com.example.task.users.Role;
import com.example.task.users.User;
import com.example.task.repository.RoleRepository;
import com.example.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository usersRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    public ResponseEntity registerUser(RegistrationRequest userDto){
        Optional<User> optionalUser = usersRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            return ResponseEntity.status(500).body("User with given email already exists");

        }else{
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEmail(userDto.getEmail());
            user.setEnabled(true);

            Optional<Role> role  = roleRepository.findByName("ROLE_USER");
            if(role.isPresent()){
                Collection<Role> roles = List.of(role.get());
                user.setRoles(roles);
            }
            usersRepository.save(user);

            return ResponseEntity.ok().body( "User registered");
        }

    }
}
