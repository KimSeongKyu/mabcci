package com.mabcci.auth.util;

import com.mabcci.auth.domain.Claim;
import com.mabcci.auth.domain.ClaimType;
import com.mabcci.auth.domain.TokenType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@NoArgsConstructor
public class JwtUtil {

    private final static String SECRET_KEY =
            "ssafy mabcci team kim kim joe lim choi";

    public String createToken(final TokenType tokenType, final String email) {
        return Jwts.builder()
                .setHeader(createClaim(ClaimType.HEADER))
                .setClaims(createPayload(tokenType, email))
                .signWith(createSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Map<String, Object> createClaim(final ClaimType claimType) {
        return Arrays.stream(Claim.values())
                .filter(claim -> claim.getType().equals(claimType))
                .collect(toMap(Claim::getKey, Claim::getValue));
    }

    public Map<String, Object> createPayload(final TokenType tokenType, final String email) {
        final Map<String, Object> payload = createClaim(ClaimType.PAYLOAD);
        final Date currentTime = new Date();

        payload.put(Claim.EXPIRATION_KEY, currentTime.getTime() + tokenType.getExpirationTime());
        payload.put(Claim.NOT_BEFORE_KEY, currentTime);
        payload.put(Claim.ISSUED_AT_KEY, currentTime);
        payload.put(Claim.EMAIL_KEY, email);

        return payload;
    }

    public Key createSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValidToken(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(createSecretKey())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
