package com.mabcci.domain.auth.service;

import com.mabcci.domain.auth.domain.RefreshToken;
import com.mabcci.domain.auth.domain.RefreshTokenRepository;
import com.mabcci.domain.auth.exception.NotLoginMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void logout(final String email) {
        final RefreshToken refreshToken = refreshTokenRepository.findByMemberEmail(email)
                .orElseThrow(() -> new NotLoginMemberException(email));

        refreshTokenRepository.delete(refreshToken);
    }
}
