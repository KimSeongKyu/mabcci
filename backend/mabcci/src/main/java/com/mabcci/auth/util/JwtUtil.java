package com.mabcci.auth.util;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@NoArgsConstructor
public class JwtUtil {

    private final static long EXPIRATION_TIME = 1000 * 60 * 30;

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

        payload.put("exp", currentTime + EXPIRATION_TIME);
        payload.put("nbf", currentTime);
        payload.put("iat", currentTime);
        payload.put("nickName", nickName);

        return payload;
    }
}
