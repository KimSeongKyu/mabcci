package com.mabcci.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LogoutRequestDto {

    private final String email;
}
