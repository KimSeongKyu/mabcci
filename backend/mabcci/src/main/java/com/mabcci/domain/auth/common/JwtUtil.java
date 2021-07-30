package com.mabcci.domain.auth.common;

import com.mabcci.domain.auth.domain.vo.Claim;
import com.mabcci.domain.auth.domain.vo.ClaimType;
import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.domain.vo.JwtTokenType;
import com.mabcci.domain.member.domain.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
public class JwtUtil {

    private final static String SECRET_KEY =
            "ssafy mabcci team kim kim joe lim choi";

    public JwtUtil() {
    }

    public JwtToken createToken(final JwtTokenType jwtTokenType, final Member member) {
        return JwtToken.of(Jwts.builder()
                .setHeader(createClaim(ClaimType.HEADER))
                .setClaims(createPayload(jwtTokenType, member))
                .signWith(createSecretKey(), SignatureAlgorithm.HS256)
                .compact());
    }

    public Map<String, Object> createClaim(final ClaimType claimType) {
        return Arrays.stream(Claim.values())
                .filter(claim -> claim.type().equals(claimType))
                .collect(toMap(Claim::key, Claim::value));
    }

    public Map<String, Object> createPayload(final JwtTokenType jwtTokenType, final Member member) {
        final Map<String, Object> payload = createClaim(ClaimType.PAYLOAD);
        final Date currentTime = new Date();

        payload.put(Claim.EXPIRATION_KEY, currentTime.getTime() + jwtTokenType.expirationTime());
        payload.put(Claim.NOT_BEFORE_KEY, currentTime);
        payload.put(Claim.ISSUED_AT_KEY, currentTime);
        payload.put(Claim.EMAIL_KEY, member.email());
        payload.put(Claim.NICKNAME_KEY, member.nickname());
        payload.put(Claim.ROLE_KEY, member.role());

        return payload;
    }

    public Key createSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValidToken(final JwtToken jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(createSecretKey())
                    .build()
                    .parseClaimsJws(jwtToken.jwtToken());

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
