package com.mabcci.auth.util;

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

    public Map<String, Object> createHeader() {
        return Arrays.stream(Claim.values())
                .filter(claim -> claim.getType().equals(ClaimType.HEADER))
                .collect(toMap(Claim::getKey, Claim::getValue));
    }

    public Map<String, Object> createPayload(final String nickName) {
        final Map<String, Object> payload = Arrays.stream(Claim.values())
                .filter(claim -> claim.getType().equals(ClaimType.PAYLOAD))
                .collect(toMap(Claim::getKey, Claim::getValue));

        final long currentTime = new Date().getTime();

        payload.put(Claim.EXPIRATION, currentTime + Claim.EXPIRATION_TIME);
        payload.put(Claim.NOT_BEFORE, currentTime);
        payload.put(Claim.ISSUED_AT, currentTime);
        payload.put(Claim.NICK_NAME, nickName);

        return payload;
    }

    public Key createSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(final String nickName) {
        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createPayload(nickName))
                .signWith(createSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
