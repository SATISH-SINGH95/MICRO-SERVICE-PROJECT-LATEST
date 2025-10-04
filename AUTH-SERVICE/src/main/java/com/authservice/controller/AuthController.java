package com.authservice.controller;

import com.authservice.model.JwtTokenResponse;
import com.authservice.model.LoginRequest;
import com.authservice.model.User;
import com.authservice.model.UserDTO;
import com.authservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register-user")
    public UserDTO saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/generate-token")
    public JwtTokenResponse generateToken(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return userService.generateToken(loginRequest.getUsername());
        }else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
