package com.ems.app.identity.model.dto;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;
    private String password;
}
