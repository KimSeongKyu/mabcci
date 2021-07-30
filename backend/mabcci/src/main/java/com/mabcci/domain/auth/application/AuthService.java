package com.mabcci.domain.auth.application;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.auth.domain.RefreshToken;
import com.mabcci.domain.auth.domain.RefreshTokenRepository;
import com.mabcci.domain.auth.domain.TokenType;
import com.mabcci.domain.auth.dto.LoginRequest;
import com.mabcci.domain.auth.dto.LoginResponse;
import com.mabcci.domain.auth.dto.LogoutRequest;
import com.mabcci.domain.auth.exception.NotLoginMemberException;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.domain.model.Email;
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
    public void logout(final LogoutRequest logoutRequest) {
        final Email email = logoutRequest.getEmail();
        final RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new NotLoginMemberException(email));

        refreshTokenRepository.delete(refreshToken);
    }

    @Transactional
    public LoginResponse login(final LoginRequest loginRequest) {
        final Email email = loginRequest.getEmail();
        final Member member = memberRepository.findByEmailAndPassword(email, loginRequest.getPassword())
                .orElseThrow(MemberNotFoundException::new);

        final String accessToken = jwtUtil.createToken(TokenType.ACCESS_TOKEN, member);
        final String refreshToken = jwtUtil.createToken(TokenType.REFRESH_TOKEN, member);

        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .build());

        return new LoginResponse(accessToken, refreshToken);
    }
}
