package com.mabcci.domain.auth.application;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.auth.domain.RefreshToken;
import com.mabcci.domain.auth.domain.RefreshTokenRepository;
import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.domain.vo.JwtTokenType;
import com.mabcci.domain.auth.dto.request.LoginRequest;
import com.mabcci.domain.auth.dto.response.LoginResponse;
import com.mabcci.domain.auth.dto.request.LogoutRequest;
import com.mabcci.domain.auth.exception.NotLoginMemberException;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Password;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public AuthService(final RefreshTokenRepository refreshTokenRepository, final MemberRepository memberRepository,
                       final JwtUtil jwtUtil) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public LoginResponse login(final LoginRequest loginRequest) {
        final Email email = loginRequest.email();
        final Member member = getMemberByEmailAndPassword(email, loginRequest.password());
        final JwtToken accessToken = jwtUtil.createToken(JwtTokenType.ACCESS_TOKEN, member);
        final JwtToken refreshToken = jwtUtil.createToken(JwtTokenType.REFRESH_TOKEN, member);

        saveRefreshToken(email, refreshToken);

        return new LoginResponse(accessToken, refreshToken);
    }

    @Transactional
    public void logout(final LogoutRequest logoutRequest) {
        final Email email = logoutRequest.email();
        deleteRefreshToken(getRefreshTokenByEmail(email));
    }

    private Member getMemberByEmailAndPassword(final Email email, final Password password) {
        return memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(MemberNotFoundException::new);
    }

    private void saveRefreshToken(final Email email, final JwtToken refreshToken) {
        refreshTokenRepository.save(buildRefreshToken(email, refreshToken));
    }

    private RefreshToken buildRefreshToken(final Email email, final JwtToken refreshToken) {
        return RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .build();
    }

    private RefreshToken getRefreshTokenByEmail(final Email email) {
        return refreshTokenRepository.findById(email)
                .orElseThrow(() -> new NotLoginMemberException(email));
    }

    private void deleteRefreshToken(final RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }
}
