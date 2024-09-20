package com.ems.app.identity.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ForgetPasswordProcessRequest {

    @NonNull
    private String otp;
    @NonNull
    private String username;
    @NonNull
    private String password;

}
