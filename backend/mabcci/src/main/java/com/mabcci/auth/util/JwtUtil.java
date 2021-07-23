package com.mabcci.auth.util;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@NoArgsConstructor
public class JwtUtil {

    public Map<String, Object> createHeader() {
        return Arrays.stream(Claim.values())
                .collect(toMap(Claim::getKey, Claim::getValue));
    }
}
