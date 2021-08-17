package com.mabcci.domain.auth.common;

import com.mabcci.domain.auth.domain.vo.Claim;
import com.mabcci.domain.auth.domain.vo.ClaimType;
import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.domain.vo.JwtTokenType;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.global.common.Nickname;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
public class JwtUtil {

    @Value("${secret.key}")
    private String secretKey;

    public JwtUtil() {
    }

    public JwtToken createToken(final JwtTokenType jwtTokenType, final Member member) {
        return JwtToken.of(getJwtTokenValue(jwtTokenType, member));
    }

    private String getJwtTokenValue(final JwtTokenType jwtTokenType, final Member member) {
        return Jwts.builder()
                .setHeader(createClaim(ClaimType.HEADER))
                .setClaims(createPayload(jwtTokenType, member))
                .signWith(createSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Map<String, Object> createClaim(final ClaimType claimType) {
        return Arrays.stream(Claim.values())
                .filter(claim -> claim.type().equals(claimType))
                .collect(toMap(Claim::key, Claim::value));
    }

    private Map<String, Object> createPayload(final JwtTokenType jwtTokenType, final Member member) {
        final Map<String, Object> payload = createClaim(ClaimType.PAYLOAD);
        setPayloads(jwtTokenType, member, payload);
        return payload;
    }

    private Key createSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private void setPayloads(final JwtTokenType jwtTokenType, final Member member, final Map<String, Object> payload) {
        final Date currentTime = new Date();

        payload.put(Claim.EXPIRATION_KEY, Long.sum(currentTime.getTime(), jwtTokenType.expirationTime()));
        payload.put(Claim.NOT_BEFORE_KEY, currentTime);
        payload.put(Claim.ISSUED_AT_KEY, currentTime);
        payload.put(Claim.EMAIL_KEY, member.email());
        payload.put(Claim.NICKNAME_KEY, member.nickname());
        payload.put(Claim.ROLE_KEY, member.memberRole());
    }

    public boolean isValidToken(final JwtToken jwtToken) {
        try {
            getClaimsJws(jwtToken.jwtToken());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String nickname(final String accessToken) {
        final Claims claims = getClaimsJws(accessToken).getBody();
        final Nickname nickname = claims.get(Claim.NICKNAME_KEY, Nickname.class);
        return nickname.nickname();
    }

    private Jws<Claims> getClaimsJws(final String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(createSecretKey())
                .build()
                .parseClaimsJws(accessToken);
    }

}
