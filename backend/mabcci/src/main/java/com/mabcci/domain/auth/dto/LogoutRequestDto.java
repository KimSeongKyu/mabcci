package com.mabcci.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class LogoutRequestDto {

    @NotEmpty
    @Email
    private String email;

    @Builder
    public LogoutRequestDto(final String email) {
        this.email = email;
    }
}
