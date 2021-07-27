package com.mabcci.domain.auth.service;

import com.mabcci.domain.auth.domain.RefreshToken;
import com.mabcci.domain.auth.domain.RefreshTokenRepository;
import com.mabcci.domain.auth.domain.TokenType;
import com.mabcci.domain.auth.dto.LoginRequest;
import com.mabcci.domain.auth.dto.LoginResponse;
import com.mabcci.domain.auth.exception.NotLoginMemberException;
import com.mabcci.domain.auth.util.JwtUtil;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void logout(final String email) {
        final RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new NotLoginMemberException(email));

        refreshTokenRepository.delete(refreshToken);
    }

    @Transactional
    public LoginResponse login(final LoginRequest loginRequest) {
        final String email = loginRequest.getEmail();
        memberRepository.findByEmailAndPassword(email, loginRequest.getPassword())
                .orElseThrow(MemberNotFoundException::new);

        final String accessToken = jwtUtil.createToken(TokenType.ACCESS_TOKEN, email);
        final String refreshToken = jwtUtil.createToken(TokenType.REFRESH_TOKEN, email);

        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .build());

        return new LoginResponse(accessToken, refreshToken);
    }
}
