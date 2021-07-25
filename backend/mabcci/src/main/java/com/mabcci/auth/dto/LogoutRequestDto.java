package com.mabcci.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LogoutRequestDto {

    private String email;

    @Builder
    public LogoutRequestDto(final String email) {
        this.email = email;
    }
}
