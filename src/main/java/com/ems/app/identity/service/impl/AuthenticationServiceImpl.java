package com.ems.app.identity.service.impl;

import com.ems.app.identity.model.dto.*;
import com.ems.app.identity.model.entity.enums.Role;
import com.ems.app.identity.model.entity.User;
import com.ems.app.identity.repository.UserRepository;
import com.ems.app.identity.service.AuthenticationService;
import com.ems.app.identity.service.JWTService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public User signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse login(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                        signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        return response;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        var userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        var user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse response = new JwtAuthenticationResponse();
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            return response;
        }
        return null;
    }

    @Override
    public void initiateForgetPassword(ForgetPasswordInitRequest forgetPasswordInitRequest) {
        // check the user by username
        var user = userRepository.findByEmail(forgetPasswordInitRequest.getUsername()).orElseThrow();
        // if present then send otp
    }

    @Override
    public void processForgetPassword(ForgetPasswordProcessRequest forgetPasswordProcessRequest) {
        // check the user by username
        var user = userRepository.findByEmail(forgetPasswordProcessRequest.getUsername()).orElseThrow();
        // verify the otp
        // update the password
        user.setPassword(forgetPasswordProcessRequest.getPassword());
        userRepository.save(user);
    }

}
