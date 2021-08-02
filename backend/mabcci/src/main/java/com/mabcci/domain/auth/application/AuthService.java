package com.mabcci.domain.auth.application;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.auth.domain.RefreshToken;
import com.mabcci.domain.auth.domain.RefreshTokenRepository;
import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.domain.vo.JwtTokenType;
import com.mabcci.domain.auth.dto.LoginRequest;
import com.mabcci.domain.auth.dto.LoginResponse;
import com.mabcci.domain.auth.dto.LogoutRequest;
import com.mabcci.domain.auth.exception.NotLoginMemberException;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.exception.MemberNotFoundException;
import com.mabcci.domain.model.Email;
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

        final JwtToken accessToken = jwtUtil.createToken(JwtTokenType.ACCESS_TOKEN, member);
        final JwtToken refreshToken = jwtUtil.createToken(JwtTokenType.REFRESH_TOKEN, member);

        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .build());

        return new LoginResponse(accessToken, refreshToken);
    }
}
