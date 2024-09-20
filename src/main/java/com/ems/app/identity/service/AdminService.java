package com.ems.app.identity.service;

import com.ems.app.identity.model.dto.SignUpRequest;
import com.ems.app.identity.model.entity.User;

public interface AdminService {
    User register(SignUpRequest signUpRequest);
}
