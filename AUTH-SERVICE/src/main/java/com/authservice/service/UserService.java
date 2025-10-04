package com.authservice.service;

import com.authservice.model.JwtTokenResponse;
import com.authservice.model.User;
import com.authservice.model.UserDTO;
import com.authservice.repository.UserRepository;
import com.authservice.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserDTO saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);
        return new UserDTO(save.getId(), save.getUsername(), save.getEmail(), save.getRoles());
    }

    public JwtTokenResponse generateToken(String username){
        String token = jwtUtil.generateToken(username);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        jwtTokenResponse.setTokenType("Bearer");
        jwtTokenResponse.setAccessToken(token);
        jwtTokenResponse.setValidUntil(jwtUtil.extractExpiration(token).toString());
        return jwtTokenResponse;
    }
}
