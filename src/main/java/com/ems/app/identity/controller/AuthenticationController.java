package com.ems.app.identity.controller;

import com.ems.app.identity.model.dto.*;
import com.ems.app.identity.model.entity.User;
import com.ems.app.identity.service.AuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@NonNull @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@NonNull @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.login(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@NonNull @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/forget-password:initiate")
    public ResponseEntity<Void> initiateForgetPassword(@NonNull @RequestBody ForgetPasswordInitRequest forgetPasswordRequest) {
        authenticationService.initiateForgetPassword(forgetPasswordRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forget-password:process")
    public ResponseEntity<Void> processForgetPassword(@NonNull @RequestBody ForgetPasswordProcessRequest forgetPasswordProcessRequest) {
        authenticationService.processForgetPassword(forgetPasswordProcessRequest);
        return ResponseEntity.noContent().build();
    }

}
