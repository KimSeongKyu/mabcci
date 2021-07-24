package com.mabcci.auth.util;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@NoArgsConstructor
public class JwtUtil {

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
}
