package com.ems.app.identity.controller;

import com.ems.app.identity.model.dto.SignUpRequest;
import com.ems.app.identity.model.entity.User;
import com.ems.app.identity.service.AdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@NonNull @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(adminService.register(signUpRequest));
    }

}
