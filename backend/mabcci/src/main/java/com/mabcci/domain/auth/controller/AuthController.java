package com.mabcci.domain.auth.controller;

import com.mabcci.domain.auth.dto.LoginRequest;
import com.mabcci.domain.auth.dto.LogoutRequestDto;
import com.mabcci.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid final LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody @Valid final LogoutRequestDto logoutRequestDto) {
        authService.logout(logoutRequestDto.getEmail());
        return ResponseEntity.noContent()
                .build();
    }
}
