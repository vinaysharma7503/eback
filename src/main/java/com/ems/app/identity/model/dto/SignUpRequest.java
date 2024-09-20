package com.ems.app.identity.model.dto;

import com.ems.app.identity.model.entity.enums.Role;
import lombok.Data;

import static com.ems.app.identity.model.entity.enums.Role.USER;

@Data
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role = USER;
}
