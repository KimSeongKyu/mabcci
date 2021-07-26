package com.mabcci.auth.controller;

import com.mabcci.auth.dto.LogoutRequestDto;
import com.mabcci.auth.service.AuthService;
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

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody @Valid final LogoutRequestDto logoutRequestDto) {
        authService.logout(logoutRequestDto.getEmail());
        return ResponseEntity.noContent()
                .build();
    }
}
