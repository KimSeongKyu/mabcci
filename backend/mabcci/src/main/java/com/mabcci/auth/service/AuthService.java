package com.mabcci.auth.service;

import com.mabcci.auth.domain.RefreshToken;
import com.mabcci.auth.domain.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void logout(final String email) {
        RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(IllegalArgumentException::new);

        refreshTokenRepository.delete(refreshToken);
    }
}
