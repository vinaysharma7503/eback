package com.ems.app.identity.service;

import com.ems.app.identity.model.dto.*;
import com.ems.app.identity.model.entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse login(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    void initiateForgetPassword(ForgetPasswordInitRequest forgetPasswordInitRequest);

    void processForgetPassword(ForgetPasswordProcessRequest forgetPasswordProcessRequest);
}
